package com.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * API-BL
 *
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages={"com.mq.mapper", "com.mq.base.mapper"})
public class ApiMqApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApiMqApplication.class, args);
    }
}
