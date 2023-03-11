package cn.fly.logDemo.config;

import cn.fly.logDemo.intercepter.RecordInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fly
 * @date 2023/3/2
 * @description
 */

@Configuration
public class WebAppConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RecordInterceptor());
    }
}
