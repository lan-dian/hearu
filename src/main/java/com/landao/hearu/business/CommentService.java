package com.landao.hearu.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;

import java.util.List;

public interface CommentService {

    boolean likeComment(Long userId, Long commentId);

    boolean unLikeComment(Long userId, Long commentId);

    boolean commentComment(String content, Long commentId);

    boolean commentTopic(String content, Long topicId);

    IPage<CommentVO> pageComment(Integer page, Integer limit,Long topicId);

    List<CommentCommentVO> listCommentComments(Long commentId, Integer limit);


}
