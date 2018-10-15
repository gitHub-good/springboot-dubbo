package com.xu.springboot.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration/*可用的dubbo配置*/
public class SpringbootDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboConsumerApplication.class, args);
    }
}
