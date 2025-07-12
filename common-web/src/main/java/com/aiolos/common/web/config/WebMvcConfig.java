package com.aiolos.common.web.config;

import com.aiolos.common.web.servlet.ContextInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(ContextInterceptor.class)
@ConditionalOnWebApplication
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Bean
    @ConditionalOnMissingBean   // 避免覆盖下游自定义拦截器
    public ContextInterceptor contextInterceptor() {
        return new ContextInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
    }
}
