package com.example.aop.demo.Controller;

import com.example.aop.demo.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/noAop")
    public String noAop( ) {
        return testService.test();
    }

    @GetMapping("/aop")
    public String aop( ) {
        return testService.testAop();
    }
}
