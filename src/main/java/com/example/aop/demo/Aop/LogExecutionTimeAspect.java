package com.example.aop.demo.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class LogExecutionTimeAspect {

    Logger logger =  LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Pointcut()
    public void onPointcut() {
        logger.info("=============== ExecutionTimeAspect onPointcut");
    }


    // Aspect와 다른 패키지에 있을 경우 경로를 적어줘여 찾을 수 있음
    @Around("@annotation(com.example.aop.demo.CustomAnnotation.LogExecutionTime)")
    public Object onAroundHandler(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        logger.info("=============== ExecutionTimeAspect onAroundHandler before !");
        // @LogExecutionTime이 붙어있는 Target 메소드 실행
        Object result = pjp.proceed();

        stopWatch.stop();
        logger.info("=============== ExecutionTimeAspect onAroundHandler after !");
        logger.info("ExecutionTimeAspect : " + result.toString());
        logger.info("time : " + stopWatch.getTotalTimeMillis());
        return result;
    }


}
