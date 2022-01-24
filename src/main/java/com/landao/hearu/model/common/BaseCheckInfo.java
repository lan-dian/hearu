package com.landao.hearu.model.common;

import com.landao.hearu.util.check.CheckUtil;
import lombok.Data;

@Data
public class BaseCheckInfo {

    /**
     * 主键
     */
    private Long id;

    public void addCheck(){
        id=null;
        commonCheck();
    }

    public void updateCheck(){
        CheckUtil.checkUpdateId(id);
        commonCheck();
    }

    protected void commonCheck(){
        CheckUtil.checkClass(this);
    }

}
