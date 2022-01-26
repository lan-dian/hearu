package com.landao.hearu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.mapper.TopicMapper;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.SelfTopicVO;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.service.ITopicService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 话题 服务实现类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Service
public class ITopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    @Override
    public void pageTopic(IPage<TopicVO> iPage, TopicType topicType, Long userId){
        this.baseMapper.pageTopic(iPage, topicType.ordinal(), userId);
    }


    @Override
    public void pageSelfTopic(IPage<SelfTopicVO> iPage, Long userId){
        this.baseMapper.pageSelfTopic(iPage,userId);
    }

}
