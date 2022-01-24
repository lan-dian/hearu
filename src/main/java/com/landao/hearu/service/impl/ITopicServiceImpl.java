package com.landao.hearu.service.impl;

import com.landao.hearu.entity.Topic;
import com.landao.hearu.mapper.TopicMapper;
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

}
