package com.xu.srpingbootdubboprovider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration/*开启dubbo自动配置支持*/
public class SrpingbootDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrpingbootDubboProviderApplication.class, args);
    }
}
