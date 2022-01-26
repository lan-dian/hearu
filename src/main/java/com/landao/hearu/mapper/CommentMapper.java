package com.landao.hearu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.landao.hearu.model.page.comment.CommentVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<CommentVO> pageCommentVO(IPage<?> iPage, @Param("userId") Long userId, @Param("topicId") Long topicId);

}
