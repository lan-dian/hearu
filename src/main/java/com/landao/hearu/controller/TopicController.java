package com.landao.hearu.controller;


import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.topic.TopicInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题相关
 */
@RestController
@RequestMapping("/topic")
public class TopicController {


    /**
     * 发布话题
     */
    @PostMapping("/publish")
    public CommonResult<Void> publish(@RequestBody TopicInfo topicInfo){
        CommonResult<Void> result=new CommonResult<>();
    }


}
