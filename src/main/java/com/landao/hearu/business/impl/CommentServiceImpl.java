package com.landao.hearu.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.landao.hearu.business.CommentService;
import com.landao.hearu.entity.Comment;
import com.landao.hearu.entity.CommentLike;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;
import com.landao.hearu.service.*;
import com.landao.web.plus.model.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    ITopicService iTopicService;

    @Resource
    ICommentService iCommentService;

    @Resource
    ICommentLikeService iCommentLikeService;

    @Resource
    ITopicLikeService iTopicLikeService;

    @Resource
    IUserService iUserService;

    @Override
    public boolean likeComment(Long userId, Long commentId){
        CommentLike old = iCommentLikeService.lambdaQuery()
                .eq(CommentLike::getUserId, userId)
                .eq(CommentLike::getCommentId, commentId)
                .one();
        if(old!=null){
            throw new BusinessException("你已经赞过了");
        }

        Integer count = iCommentService.lambdaQuery().eq(Comment::getId, commentId).count();
        if(count==0){
            throw new BusinessException("评论不存在");
        }

        CommentLike commentLike = new CommentLike();
        commentLike.setCommentId(commentId);
        commentLike.setUserId(userId);

        return iCommentLikeService.save(commentLike);
    }

    @Override
    public boolean unLikeComment(Long userId,Long commentId){
        return iCommentLikeService.lambdaUpdate()
                .eq(CommentLike::getUserId, userId)
                .eq(CommentLike::getCommentId, commentId)
                .remove();
    }

    @Override
    public boolean commentComment(String content, Long commentId,Long userId){
        //判断评论是否存在
        Comment parentComment = iCommentService.lambdaQuery().eq(Comment::getId, commentId).one();
        if(parentComment==null){
            throw new BusinessException("评论不存在");
        }
        //如果是直接评论评论
        Comment comment=null;

        if(parentComment.getParentId()==null){
            comment = new Comment();
            comment.setContent(content);
            comment.setUserId(userId);
            comment.setTopicId(parentComment.getTopicId());
            comment.setParentId(parentComment.getId());
        }else {//在评论中回复了别人
            //得到被回复人的id
            Long parentUserId = parentComment.getUserId();
            //寻找顶层的父id,方便设置成嵌套结构
            Comment topComment = iCommentService.lambdaQuery()
                    .eq(Comment::getId, parentComment.getParentId()).one();

            comment = new Comment();
            comment.setContent(content);
            comment.setUserId(userId);
            comment.setTopicId(parentComment.getTopicId());
            comment.setParentId(topComment.getId());
            comment.setResponseId(parentUserId);
        }

        return iCommentService.save(comment);
    }

    @Override
    public boolean commentTopic(String content, Long topicId,Long userId) {
        Topic topic = iTopicService.lambdaQuery().eq(Topic::getId, topicId).one();
        if (topic == null) {
            throw new BusinessException("话题不存在");
        }

        Comment comment = new Comment();
        comment.setContent(content)
                .setUserId(userId)
                .setTopicId(topicId);

        return iCommentService.save(comment);
    }


    @Override
    public IPage<CommentVO> pageComment(Integer page, Integer limit, Long topicId,Long userId){
        IPage<CommentVO> iPage=new Page<>(page,limit);
        iCommentService.pageComment(iPage,userId,topicId);
        return iPage;
    }

    @Override
    public List<CommentCommentVO> listCommentComments(Long commentId, Integer limit,Long userId){
        return iCommentService.listCommentCommentVO(commentId, userId, limit);
    }

}
