package com.landao.hearu.model.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页包装类
 */
@Data
@AllArgsConstructor
public class PageDTO<T> {

    /**
     * 数据总条数
     */
    private Long n;

    /**
     * 数据列表
     */
    private List<T> data;

    public static <T> PageDTO<T> build(Long n,List<T> data){
        return new PageDTO<>(n,data);
    }

    public static <T> PageDTO <T> build(IPage<T> iPage){
        return new PageDTO<>(iPage.getTotal(),iPage.getRecords());
    }

}
