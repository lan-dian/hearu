package com.landao.hearu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.hearu.business.CommentService;
import com.landao.hearu.business.TopicService;
import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.common.PageDTO;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.model.topic.TopicInfo;
import com.landao.hearu.safe.annotations.RequiredLogin;
import com.landao.hearu.util.check.CheckUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 话题相关
 */
@RequiredLogin
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource
    TopicService topicService;

    @Resource
    CommentService commentService;

    /**
     * 发布话题
     */
    @PostMapping("/publish")
    public CommonResult<Void> publish(@RequestBody TopicInfo topicInfo){
        CommonResult<Void> result=new CommonResult<>();
        topicInfo.addCheck();

        boolean publish = topicService.publish(topicInfo, TopicType.User);

        return result.ok(publish);
    }

    /**
     * 给话题点赞
     */
    @GetMapping("/like/{topicId}")
    public CommonResult<Void> likeTopic(@PathVariable Long topicId){
        CommonResult<Void> result=new CommonResult<>();

        CheckUtil.checkId(topicId);

        boolean likeTopic = topicService.likeTopic(topicId);

        return result.ok(likeTopic);
    }

    /**
     * 取消对话题的点赞
     */
    @GetMapping("/unlike/{topicId}")
    public CommonResult<Void> unlikeTopic(@PathVariable Long topicId){
        CommonResult<Void> result=new CommonResult<>();

        CheckUtil.checkId(topicId);

        boolean unlikeTopic = topicService.unlikeTopic(topicId);

        return result.ok(unlikeTopic,"你还没有点过赞呢");
    }

    /**
     * 评论话题
     * @param topicId 话题id,在url中传递/comment/topic/518
     * @param content 评论内容,通过表单传递,最长为1024
     */
    @PostMapping("/comment/{topicId}")
    public CommonResult<Void> commentTopic(@PathVariable Long topicId,
                                           @RequestParam String content){
        CommonResult<Void> result=new CommonResult<>();

        CheckUtil.checkId(topicId);

        CheckUtil.checkNotBlank(content,"评论内容");
        CheckUtil.checkStringLength(content,"评论内容",1024);

        boolean comment = commentService.commentTopic(content, topicId);

        return result.ok(comment);
    }


    /**
     * 评论的评论
     * @param commentId 评论的id
     * @param content 评论的内容
     */
    @PostMapping("/comment/comment/{commentId}")
    public CommonResult<Void> commentComment(@PathVariable Long commentId,
                                             @RequestParam String content){
        CommonResult<Void> result=new CommonResult<>();

        CheckUtil.checkId(commentId);

        CheckUtil.checkNotBlank(content,"评论内容");
        CheckUtil.checkStringLength(content,"评论内容",1024);

        boolean commentComment = commentService.commentComment(content, commentId);

        return result.ok(commentComment);
    }

    /**
     * 分页获取话题
     */
    @GetMapping("/page")
    public CommonResult<PageDTO<TopicVO>> page(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "15") Integer limit){
        CommonResult<PageDTO<TopicVO>> result=new CommonResult<>();

        CheckUtil.checkNotNegative(page,"起始页数");
        CheckUtil.checkNotNegative(limit,"分页条数");

        IPage<TopicVO> iPage = topicService.pageTopic(page, limit);

        return result.body(PageDTO.build(iPage));
    }



}
