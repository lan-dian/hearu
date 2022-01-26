package com.landao.hearu.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.landao.hearu.business.CommentService;
import com.landao.hearu.entity.Comment;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;
import com.landao.hearu.service.*;
import com.landao.hearu.util.TokenUtil;
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
    public boolean commentComment(String content, Long commentId){
        //判断评论是否存在
        Comment parentComment = iCommentService.lambdaQuery().eq(Comment::getId, commentId).one();
        if(parentComment==null){
            throw new BusinessException("评论不存在");
        }
        Long userId = TokenUtil.getUserId();
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
    public boolean commentTopic(String content, Long topicId) {
        Topic topic = iTopicService.lambdaQuery().eq(Topic::getId, topicId).one();
        if (topic == null) {
            throw new BusinessException("话题不存在");
        }

        Comment comment = new Comment();
        comment.setContent(content)
                .setUserId(TokenUtil.getUserId())
                .setTopicId(topicId);

        return iCommentService.save(comment);
    }


    @Override
    public IPage<CommentVO> pageComment(Integer page, Integer limit, Long topicId){
        IPage<CommentVO> iPage=new Page<>(page,limit);
        iCommentService.pageComment(iPage,TokenUtil.getUserId(),topicId);
        return iPage;
    }

    @Override
    public List<CommentCommentVO> listCommentComments(Long commentId,Integer limit){
        return iCommentService.listCommentCommentVO(commentId, TokenUtil.getUserId(), limit);
    }

}
