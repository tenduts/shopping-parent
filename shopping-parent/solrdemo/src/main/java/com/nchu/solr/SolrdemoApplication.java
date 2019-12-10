package com.nchu.solr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
@MapperScan("com.nchu.mapper")
@SpringBootApplication
public class SolrdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolrdemoApplication.class, args);
    }

}
