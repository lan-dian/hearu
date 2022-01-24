package com.landao.hearu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.landao.hearu.mapper")
public class HearuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HearuApplication.class, args);
        log.info("http://127.0.0.1:8002/doc/index.html");
    }

}
