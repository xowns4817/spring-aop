package com.example.aop.demo.Aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JoinPoint : 모듈의 기능이 삽입되어 동작할 수 있는 실행 가능한 특정 위치
 * PointCut : 어떤 클래스의 어느 JoinPoint를 사용할 것인지를 결정
 *
 * @Before : AOP가 적용될 메서드가 실행되기 전의 시점
 *
 * @After : AOP가 적용될 메서드가 실행된 이후의 시점. 메소드가 성공적으로 수행된 경우와 에러가 발생하여 Exception이 생긴 경우 모두 포함
 *
 * @AfterReturning : AOP가 적용될 메서드가 에러없이 성공적으로 실행된 이후의 시점
 *
 * @AfterThrowing : AOP가 적용될 메서드가 에러가 발생하여 Exception을 던지는 시점
 *
 * @Around : AOP가 적용될 메소드의 시작부터 끝까지 전반적인 시점을 모두 관리
 *
 *
 * execution안에 들어가는 파라미터 설명
 * -> *            com.example.aop.demo.Service.    *.       *Aop(..))
 * -> 리턴 타입,    패키지 경로                       클래스명    메소드명
 *
 *
 */
@Aspect
@Component
public class LogAspect {
    Logger logger =  LoggerFactory.getLogger(LogAspect.class);

    /**
     *      * 해당 빈이 생성될때 즉, 스프링에 의해 주입이 될때 호출
     *      * @PostConstruct란 ?
     *      * - @PostConstruct는 의존성 주입이 이루어진 후 초기화를 수행하는 메서드이다. -> 빈이 생성됨과 동시에 초기화가 가능하다.
     *      *
     */
    @PostConstruct
    public void postConstruct( ) {
        logger.info("postConstruct call !");
    };

    // 해당 빈이 소멸될때 호출
    @PreDestroy
    public void preDestroy( ) {
        logger.info("preDestroy call !");
    };

    @Before("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public void onBeforeHandler(JoinPoint joinPoint) {
        logger.info("=============== onBeforeThing");
    }

    @After("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public void onAfterHandler(JoinPoint joinPoint) {
        logger.info("=============== onAfterHandler");
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.demo.Service.*.*Aop(..))", returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
        logger.info("@AfterReturning : " + str);
        logger.info("=============== onAfterReturningHandler");
    }

    @AfterThrowing(pointcut="execution(* com.example.aop.demo.Service.*.*Aop(..))", throwing="exception")
    public void afterThrowingMethod(JoinPoint jp, Exception exception) throws Exception {
        logger.info("afterThrowingMethod() called.....");
    }

    @Around("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public Object onAroundHandler(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("=============== onAroundHandler before !");
        Object result = pjp.proceed();
        logger.info("=============== onAroundHandler after !");
        return result;
    }
    /*
    @Pointcut("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public void onPointcut(JoinPoint joinPoint) {
        logger.info("=============== onPointcut");
    }
     */
}
