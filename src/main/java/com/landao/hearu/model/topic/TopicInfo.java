package com.landao.hearu.model.topic;


import com.landao.checker.annotations.Check;
import com.landao.checker.annotations.special.group.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicInfo{

    @Id
    private Long id;

    /**
     * 标题
     */
    @Check(value = "标题",max= 128)
    private String title;

    /**
     * 内容
     */
    @Check(value = "内容",max = 50000)
    private String content;


}
