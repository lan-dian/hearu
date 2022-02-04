package com.landao.hearu.model.topic;

import com.landao.inspector.annotations.InspectField;
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
    @InspectField(value = "标题",max= 128)
    private String title;

    /**
     * 内容
     */
    @InspectField(value = "内容",max = 50000)
    private String content;


}
