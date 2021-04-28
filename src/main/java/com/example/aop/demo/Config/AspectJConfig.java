package com.example.aop.demo.Config;

import com.example.aop.demo.Aop.CacheAspect;
import com.example.aop.demo.Aop.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJConfig {

    // 빈 선언 순서에 따라 어떤 aop가 먼저 적용될지 결정된다.
    @Bean
    public CacheAspect cacheAspect( ) {
        return new CacheAspect();
    }

    @Bean
    public LogAspect logAspect( ) {
        return new LogAspect();
    }

}
