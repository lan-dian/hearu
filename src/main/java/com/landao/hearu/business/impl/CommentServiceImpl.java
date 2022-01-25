package com.landao.hearu.business.impl;

import com.landao.hearu.business.CommentService;
import com.landao.hearu.entity.Comment;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.service.*;
import com.landao.hearu.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
