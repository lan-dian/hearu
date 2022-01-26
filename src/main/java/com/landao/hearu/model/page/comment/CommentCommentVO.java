package com.landao.hearu.model.page.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 评论的评论
 */
@Data
@NoArgsConstructor
public class CommentCommentVO {

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 内容
     */
    private String content;

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
     * 是否点过赞
     */
    private Boolean like;

    /**
     * 是否为自己的评论
     */
    private Boolean mine;

    /**
     * 回复人
     */
    private SimpleUserVO response;


}
