package com.jsong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jsong.mapper")
public class DormitoryspApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryspApplication.class, args);
    }

}
