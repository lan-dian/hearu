package com.landao.hearu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.entity.Topic;
import com.landao.hearu.model.page.topic.SelfTopicVO;
import com.landao.hearu.model.page.topic.TopicVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 话题 Mapper 接口
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface TopicMapper extends BaseMapper<Topic> {

    void pageTopic(IPage<TopicVO> iPage, @Param("topicType") Integer topicType, @Param("userId") Long userId);

    void pageSelfTopic(@Param("iPage") IPage<SelfTopicVO> iPage,@Param("userId") Long userId);

}
