package com.landao.hearu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.landao.hearu.model.common.LogDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RequestLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 访问的网址
     */
    private String url;

    /**
     * 传递的参数
     */
    private String args;

    /**
     * 返回的数据
     */
    private String returns;

    /**
     * 毫秒,访问耗时
     */
    private Integer timeConsume;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类与方法名
     */
    private String classMethod;


    public RequestLog(LogDTO logDTO){
        BeanUtils.copyProperties(logDTO,this);
        long endTime = System.currentTimeMillis();
        timeConsume= Math.toIntExact(endTime - logDTO.getStartTime());
    }


}
