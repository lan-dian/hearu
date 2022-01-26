package com.landao.hearu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface ICommentService extends IService<Comment> {

    void pageComment(IPage<CommentVO> iPage,Long userId,Long topicId);

    List<CommentCommentVO> listCommentCommentVO(Long commentId, Long userId, Integer limit);
}
