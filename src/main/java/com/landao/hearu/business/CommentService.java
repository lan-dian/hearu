package com.landao.hearu.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.model.page.comment.CommentVO;

public interface CommentService {

    boolean commentComment(String content, Long commentId);

    boolean commentTopic(String content, Long topicId);

    IPage<CommentVO> pageComment(Integer page, Integer limit);
}
