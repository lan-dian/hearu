package com.landao.hearu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Comment;
import com.landao.hearu.mapper.CommentMapper;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;
import com.landao.hearu.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Service
public class ICommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public void pageComment(IPage<CommentVO> iPage, Long userId, Long topicId){
        this.baseMapper.pageCommentVO(iPage, userId, topicId);
        List<CommentVO> comments = iPage.getRecords();
        for (CommentVO comment : comments) {
            Long commentCount = comment.getCommentCount();
            if(commentCount>3){
                commentCount=3L;
            }
            List<CommentCommentVO> commentCommentVOS = listCommentCommentVO(userId, comment.getCommentId(), Math.toIntExact(commentCount));
            comment.setComments(commentCommentVOS);
        }
    }

    @Override
    public List<CommentCommentVO> listCommentCommentVO(Long commentId,Long userId,Integer limit){
        return this.baseMapper.listCommentCommentVO(userId, commentId, limit);
    }

}
