package com.zdpx.weekly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.zdpx.weekly.*")
@SpringBootApplication
@MapperScan("com.zdpx.weekly.mapper")
public class WeeklyApplication  {
//extends SpringBootServletInitializer
    public static void main(String[] args) {
        SpringApplication.run(WeeklyApplication.class, args);
    }

}
