package cn.fly;

import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaAuditing
@MapperScan("cn.fly.logDemo.infoResolver.dao")
public class LogApplication {

    public static void main(String[] args) {
        /* 设置本地时区 */
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(LogApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
        return registry -> registry.config().commonTags("application", applicationName);
    }

}
