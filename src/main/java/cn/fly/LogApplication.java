package cn.fly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@SpringBootApplication
@MapperScan("cn.fly.logDemo.infoResolver.dao")
public class LogApplication {

    public static void main(String[] args) {
        /* 设置本地时区 */
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(LogApplication.class, args);
    }

}
