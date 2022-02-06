package com.landao.hearu.model.topic;

import com.landao.inspector.annotations.Inspected;
import com.landao.inspector.annotations.special.group.Id;
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
    @Inspected(value = "标题",max= 128)
    private String title;

    /**
     * 内容
     */
    @Inspected(value = "内容",max = 50000)
    private String content;


}
