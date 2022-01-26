package com.landao.hearu.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.landao.hearu.business.TopicService;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.entity.TopicLike;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.model.topic.TopicInfo;
import com.landao.hearu.service.*;
import com.landao.hearu.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TopicServiceImpl implements TopicService {

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
    public IPage<TopicVO> pageTopic(Integer page, Integer limit){
        IPage<TopicVO> iPage=new Page<>(page,limit);

        iTopicService.pageTopic(iPage,TopicType.User,TokenUtil.getUserId());

        return iPage;
    }

    @Override
    public boolean unlikeTopic(Long topicId){
        Long userId = TokenUtil.getUserId();

        return iTopicLikeService.lambdaUpdate()
                .eq(TopicLike::getTopicId, topicId)
                .eq(TopicLike::getUserId, userId)
                .remove();
    }

    @Override
    public boolean likeTopic(Long topicId){
        Long userId = TokenUtil.getUserId();

        Integer hasTopic = iTopicService.lambdaQuery().eq(Topic::getId, topicId).count();
        if(hasTopic==0){
            throw new BusinessException("话题不存在");
        }

        Integer count = iTopicLikeService.lambdaQuery()
                .eq(TopicLike::getTopicId, topicId)
                .eq(TopicLike::getUserId,userId)
                .count();
        if(count!=0){
            throw new BusinessException("你已经点过赞了");
        }

        TopicLike topicLike = new TopicLike();
        topicLike.setTopicId(topicId);
        topicLike.setUserId(userId);

        return iTopicLikeService.save(topicLike);
    }


    @Override
    public boolean publish(TopicInfo topicInfo, TopicType topicType) {
        Topic topic = Topic.convert(topicInfo, topicType);
        return iTopicService.save(topic);
    }



}
