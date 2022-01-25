package com.landao.hearu.controller;

import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.safe.annotations.RequiredLogin;
import com.landao.hearu.util.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共访问接口
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    /**
     * 文件上传
     * @param file 文件
     * @return 文件上传成功会返回文件地址字符串在data
     * 返回示例/res/hearu/2022-1-25/uuid.jpg
     * 需要前端拼接服务器ip和端口
     */
    @RequiredLogin
    @PostMapping("/upload")
    public CommonResult<String> upload(@RequestParam MultipartFile file){
        CommonResult<String> result=new CommonResult<>();

        if(file.isEmpty()){
            return result.err("文件不能为空");
        }

        String resAddress = FileUploadUtil.uploadFile(file);

        return result.body(resAddress);
    }

}
