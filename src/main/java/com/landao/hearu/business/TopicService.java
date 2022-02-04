package com.landao.hearu.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.SelfTopicVO;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.model.topic.TopicInfo;

public interface TopicService {

    IPage<TopicVO> pageTopic(Integer page, Integer limit, Long userId);

    boolean unlikeTopic(Long topicId, Long userId);

    boolean likeTopic(Long topicId, Long userId);

    boolean publish(TopicInfo topicInfo, TopicType topicType, Long userId);

    IPage<SelfTopicVO> pageSelfTopicVO(Integer page, Integer limit, Long userId);

}
