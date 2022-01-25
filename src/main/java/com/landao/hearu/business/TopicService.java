package com.landao.hearu.business;

import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.topic.TopicInfo;

public interface TopicService {

    boolean likeTopic(Long topicId);

    boolean publish(TopicInfo topicInfo, TopicType topicType);

}
