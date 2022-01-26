package com.landao.hearu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.TopicVO;

/**
 * <p>
 * 话题 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface ITopicService extends IService<Topic> {

    void pageTopic(IPage<TopicVO> iPage, TopicType topicType, Long userId);

}
