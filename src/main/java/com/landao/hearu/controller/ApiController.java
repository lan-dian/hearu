package com.landao.hearu.controller;

import com.landao.hearu.model.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共访问接口
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    /**
     * 日志测试接口
     * @return
     */
    @GetMapping("/test")
    public CommonResult<Void> test(){
        CommonResult<Void> result=new CommonResult<>();
        return result;
    }


}
