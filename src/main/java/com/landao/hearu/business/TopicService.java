package com.landao.hearu.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.model.topic.TopicInfo;

public interface TopicService {

    IPage<TopicVO> pageTopic(Integer page, Integer limit);

    boolean unlikeTopic(Long topicId);

    boolean likeTopic(Long topicId);

    boolean publish(TopicInfo topicInfo, TopicType topicType);

}
