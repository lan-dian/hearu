package com.landao.hearu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.landao.guardian.annotations.author.RequiredLogin;
import com.landao.hearu.author.UserService;
import com.landao.hearu.business.CommentService;
import com.landao.hearu.business.TopicService;
import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.common.PageDTO;
import com.landao.hearu.model.enums.TopicType;
import com.landao.hearu.model.page.comment.CommentCommentVO;
import com.landao.hearu.model.page.comment.CommentVO;
import com.landao.hearu.model.page.topic.SelfTopicVO;
import com.landao.hearu.model.page.topic.TopicVO;
import com.landao.hearu.model.topic.TopicInfo;
import com.landao.inspector.annotations.Inspected;
import com.landao.inspector.model.enums.TrimType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    UserService userService;

    /**
     * 发布话题
     */
    @PostMapping("/publish")
    public CommonResult<Void> publish(@RequestBody TopicInfo topicInfo) {
        CommonResult<Void> result = new CommonResult<>();

        boolean publish = topicService.publish(topicInfo, TopicType.User, userService.getUserId());

        return result.ok(publish);
    }

    /**
     * 给话题点赞
     */
    @GetMapping("/like/{topicId}")
    public CommonResult<Void> likeTopic(@PathVariable Long topicId) {
        CommonResult<Void> result = new CommonResult<>();

        boolean likeTopic = topicService.likeTopic(topicId, userService.getUserId());

        return result.ok(likeTopic);
    }

    /**
     * 取消对话题的点赞
     */
    @GetMapping("/unlike/{topicId}")
    public CommonResult<Void> unlikeTopic(@PathVariable Long topicId) {
        CommonResult<Void> result = new CommonResult<>();


        boolean unlikeTopic = topicService.unlikeTopic(topicId, userService.getUserId());

        return result.ok(unlikeTopic, "你还没有点过赞呢");
    }

    /**
     * 评论话题
     *
     * @param topicId 话题id,在url中传递/comment/topic/518
     * @param content 评论内容,通过表单传递,最长为1024
     */
    @PostMapping("/comment/{topicId}")
    public CommonResult<Void> commentTopic(@PathVariable Long topicId,
                                           @Inspected(value = "评论内容", max = 1024, trimType = TrimType.Trail)
                                           @RequestParam String content) {
        CommonResult<Void> result = new CommonResult<>();

        boolean comment = commentService.commentTopic(content, topicId, userService.getUserId());

        return result.ok(comment);
    }


    /**
     * 评论的评论
     *
     * @param commentId 评论的id
     * @param content   评论的内容
     */
    @PostMapping("/comment/comment/{commentId}")
    public CommonResult<Void> commentComment(@PathVariable Long commentId,
                                             @Inspected(value = "评论内容", max = 1024, trimType = TrimType.Trail)
                                             @RequestParam String content) {
        CommonResult<Void> result = new CommonResult<>();

        boolean commentComment = commentService.commentComment(content, commentId, userService.getUserId());

        return result.ok(commentComment);
    }

    /**
     * 分页获取话题
     */
    @GetMapping("/page")
    public CommonResult<PageDTO<TopicVO>> page(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "15") Integer limit) {
        CommonResult<PageDTO<TopicVO>> result = new CommonResult<>();

        IPage<TopicVO> iPage = topicService.pageTopic(page, limit, userService.getUserId());

        return result.body(PageDTO.build(iPage));
    }

    /**
     * 分页获取评论
     */
    @GetMapping("/comment/page/{topicId}")
    public CommonResult<PageDTO<CommentVO>> commentPage(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "15") Integer limit,
                                                        @PathVariable Long topicId) {
        CommonResult<PageDTO<CommentVO>> result = new CommonResult<>();

        /*CheckUtil.checkNotNegative(page, "起始页数");
        CheckUtil.checkNotNegative(limit, "分页条数");*/

        IPage<CommentVO> iPage = commentService.pageComment(page, limit, topicId, userService.getUserId());

        return result.body(PageDTO.build(iPage));
    }

    /**
     * 获取评论的评论
     *
     * @param limit 可以通过comment的commentCount设置
     */
    @GetMapping("/comment/inner/{commentId}")
    public CommonResult<List<CommentCommentVO>> commentComments(@PathVariable Long commentId,
                                                                @RequestParam Integer limit) {
        CommonResult<List<CommentCommentVO>> result = new CommonResult<>();

        List<CommentCommentVO> commentCommentVOS = commentService.listCommentComments(commentId, limit, userService.getUserId());

        return result.body(commentCommentVOS);
    }

    /**
     * 点赞评论
     */
    @GetMapping("/comment/like/{commentId}")
    public CommonResult<Void> likeComment(@PathVariable Long commentId) {
        CommonResult<Void> result = new CommonResult<>();

        boolean like = commentService.likeComment(userService.getUserId(), commentId);

        return result.ok(like);
    }

    /**
     * 取消点赞评论
     */
    @GetMapping("/comment/unlike/{commentId}")
    public CommonResult<Void> unlikeComment(@PathVariable Long commentId) {
        CommonResult<Void> result = new CommonResult<>();

        boolean like = commentService.unLikeComment(userService.getUserId(), commentId);

        return result.ok(like, "你还没有点过赞呢");
    }

    /**
     * 获取我发布过的话题
     */
    @GetMapping("/mine")
    public CommonResult<PageDTO<SelfTopicVO>> mineTopic(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "15") Integer limit) {
        CommonResult<PageDTO<SelfTopicVO>> result = new CommonResult<>();

        /*CheckUtil.checkNotNegative(page, "起始页数");
        CheckUtil.checkNotNegative(limit, "分页条数");*/

        IPage<SelfTopicVO> iPage = topicService.pageSelfTopicVO(page, limit, userService.getUserId());

        return result.body(PageDTO.build(iPage));
    }


}
