package com.landao.hearu.model.topic;

import com.landao.hearu.model.common.BaseCheckInfo;
import com.landao.hearu.util.check.annotaions.FieldCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TopicInfo extends BaseCheckInfo {

    /**
     * 标题
     */
    @FieldCheck(value = "标题",maxLength = 128)
    private String title;

    /**
     * 内容
     */
    @FieldCheck(value = "内容",maxLength = 50000)
    private String content;


}
