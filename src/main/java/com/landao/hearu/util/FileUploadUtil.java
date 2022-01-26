package com.landao.hearu.util;

import com.landao.hearu.model.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Slf4j
public class FileUploadUtil {


    public static String uploadFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (StringUtils.isBlank(originalName)) {
            throw new BusinessException("原始文件名不得为空");
        }

        String extendName = originalName.substring(originalName.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID() + "." + extendName;
        LocalDate now = LocalDate.now();
        String realPath = "/home/data/hearu/" + now;
        File dir = new File(realPath);
        if (!dir.exists()) {
            boolean makeDir = dir.mkdirs();
            if(!makeDir){
                throw new BusinessException("文件夹创建失败",-999);
            }
        }
        try {
            file.transferTo(new File(dir, fileName));
        } catch (IOException e) {
            log.error(JsonUtil.parse(e));
            throw new BusinessException("文件上传失败");
        }


        return "/hearu/" + now + "/" + fileName;

    }

}
