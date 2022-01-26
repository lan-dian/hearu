package com.landao.hearu.model.page.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 话题的评论
 */
@Data
@NoArgsConstructor
public class CommentVO {

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 话题id
     */
    private Long topicId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 评论的作者
     */
    private SimpleUserVO author;

    /**
     * 点赞的数量
     */
    private Long likeCount;

    /**
     * 评论的数量
     */
    private Long commentCount;

    /**
     * 是否点过赞
     */
    private Boolean like;

    /**
     * 是否为自己的评论
     */
    private Boolean mine;

    /**
     * 该评论的评论
     * @apiNote  默认只显示3个,需要根据commentCount的数量向后台分页获取
     */
    private List<CommentCommentVO> comments;

}
