package com.nchu.sellergoods;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.nchu.mapper")
@SpringBootApplication
public class SellergoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellergoodsServiceApplication.class, args);
    }

}
