package com.example.aop.demo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(2)
public class CacheAspect {
    Logger logger =  LoggerFactory.getLogger(CacheAspect.class);

    @Pointcut("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public void onPointcut() {
        logger.info("=============== CacheAspect onPointcut");
    }

    /*
    // 대상 객체의 메서드 호출 전에 실행
    @Before("onPointcut()")
    public void onBeforeHandler(JoinPoint joinPoint) {
        logger.info("=============== CacheAspect onBeforeThing");
    }

    // 대상 객체의 메서드 호출 후에 실행
    @After("onPointcut()")
    public void onAfterHandler(JoinPoint joinPoint) {
        logger.info("=============== CacheAspect onAfterHandler");
    }

    // 대상 객체의 메서드가 익셉션 없이 실행된 이후에 실행 ( return 이 있는 어노테이션은 return 값을 파라미터로 받을 수 있는 거같음 )
    @AfterReturning(pointcut = "onPointcut()", returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
        logger.info("@AfterReturning : " + str);
        logger.info("=============== CacheAspect onAfterReturningHandler");
    }

    // 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우
    @AfterThrowing(pointcut="onPointcut()", throwing="exception")
    public void afterThrowingMethod(JoinPoint jp, Exception exception) throws Exception {
        logger.info("CacheAspect afterThrowingMethod() called.....");
    }
    */
    // 대상 객체의 메서드 실행 전, 후 또는 익셉션이 발생한 시점에 공통 기능 실행
    @Around("onPointcut()")
    public Object onAroundHandler(ProceedingJoinPoint pjp) throws Throwable {

    /*
        logger.info(pjp.getSignature().getName()); // aop 가 갈려있는 메소드
        logger.info(pjp.getSignature().toLongString());
        logger.info(pjp.getSignature().toShortString());

        logger.info(pjp.getTarget().getClass().getSimpleName()); // aop 가 걸려있는 메소드의 클래스
        logger.info(Arrays.toString(pjp.getArgs()));
    */
        logger.info("=============== CacheAspect onAroundHandler before !");
        Object result = pjp.proceed();
        logger.info("=============== CacheAspect onAroundHandler after !");
        logger.info("CacheAspect : " + result.toString());
        return result;
    }
}
