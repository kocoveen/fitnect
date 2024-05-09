package com.ssafy.fitnect.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.fitnect.model.dao")
public class DBConfig {

}
