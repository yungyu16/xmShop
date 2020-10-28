package com.xmlvhy.front.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CreatedDate: 2020/10/28
 * Author: songjialin
 */
@SpringBootApplication(scanBasePackages = "com.xmlvhy.shop")
@MapperScan("com.xmlvhy.shop.admin.dal.mapper")
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
}
