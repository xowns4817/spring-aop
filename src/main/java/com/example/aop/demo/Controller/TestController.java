package com.example.aop.demo.Controller;

import com.example.aop.demo.CustomAnnotation.LogExecutionTime;
import com.example.aop.demo.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @LogExecutionTime
    @GetMapping("/noAop")
    public String noAop( ) {
        return testService.test();
    }

    @GetMapping("/aop")
    public String aop(@RequestParam int a) {
        return testService.testAop(a);
    }
}
