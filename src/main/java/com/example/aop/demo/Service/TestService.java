package com.example.aop.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    Logger logger =  LoggerFactory.getLogger(TestService.class);

    public String test( ) {
        String msg = "Hello, Spring Boot No AOP";
        logger.info(msg);
        return msg;
    };

    public String testAop( ) {
        String msg = "Hello, Spring Boot AOP";
        logger.info(msg);
        return msg;
    };
}
