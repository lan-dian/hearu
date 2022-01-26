package com.landao.hearu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.landao.hearu.model.page.comment.CommentVO;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface ICommentService extends IService<Comment> {

    IPage<CommentVO> pageComment(IPage<?> iPage);
}
