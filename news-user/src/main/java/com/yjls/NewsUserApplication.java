package com.yjls;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
@MapperScan("com.yjls.mapper")
public class NewsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsUserApplication.class, args);
	}

}
