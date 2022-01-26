package com.landao.hearu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.mapper.TopicMapper;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public IPage<TopicVO> pageTopic(IPage<?> iPage, TopicType topicType, Long userId){
        return this.baseMapper.pageTopic(iPage, topicType.ordinal(), userId);
    }

}
