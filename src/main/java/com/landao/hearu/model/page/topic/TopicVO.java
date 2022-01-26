package com.landao.hearu.model.page.topic;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户发布的话题
 */
@Data
@NoArgsConstructor
public class TopicVO {

    /**
     * 话题id
     */
    private Long topicId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 我是否赞过
     */
    private Boolean like;

    /**
     * 话题的作者信息
     */
    private DetailUserVO author;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 是否为我的
     */
    private Boolean mine;

}
