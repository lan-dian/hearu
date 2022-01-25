package com.landao.hearu.business;

public interface CommentService {

    boolean commentComment(String content, Long commentId);

    boolean commentTopic(String content, Long topicId);

}
