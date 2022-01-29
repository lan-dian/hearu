package com.landao.hearu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@MapperScan("com.landao.hearu.mapper")
public class HearuApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HearuApplication.class, args);
        /*String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            log.info(beanDefinitionName);
        }
        run.getBean(DispatcherServlet.class);*/
        log.info("http://127.0.0.1:8002/doc/index.html");
    }

}
