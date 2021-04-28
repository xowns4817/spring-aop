package com.example.aop.demo.Service;

import com.example.aop.demo.Aop.CacheAspect;
import com.example.aop.demo.Aop.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    Logger logger =  LoggerFactory.getLogger(TestService.class);
    @Autowired
    CacheAspect cacheAspect;

    @Autowired
    LogAspect logAspect;


    public String test( ) {
        String msg = "Hello, Spring Boot No AOP";
        logger.info(msg);
        return msg;
    };

    public String testAop(int a) {
        String msg = "Hello, Spring Boot AOP " + a;
        logger.info(msg);
        return msg;
    };
}
