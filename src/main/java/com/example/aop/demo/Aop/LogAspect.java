package com.example.aop.demo.Aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

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
@Order(1)
public class LogAspect {
    Logger logger =  LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.example.aop.demo.Service.*.*Aop(..))")
    public void onPointcut() {
        logger.info("=============== LogAspect onPointcut");
    }

    /*
    // 대상 객체의 메서드 호출 전에 실행
    @Before("onPointcut()")
    public void onBeforeHandler(JoinPoint joinPoint) {
        logger.info("=============== LogAspect onBeforeThing");
    }

    // 대상 객체의 메서드 호출 후에 실행
    @After("onPointcut()")
    public void onAfterHandler(JoinPoint joinPoint) {
        logger.info("=============== LogAspect onAfterHandler");
    }

    // 대상 객체의 메서드가 익셉션 없이 실행된 이후에 실행
    @AfterReturning(pointcut = "onPointcut()", returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
        logger.info("@AfterReturning : " + str);
        logger.info("=============== LogAspect onAfterReturningHandler");
    }

    // 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우
    @AfterThrowing(pointcut="onPointcut()", throwing="exception")
    public void afterThrowingMethod(JoinPoint jp, Exception exception) throws Exception {
        logger.info("LogAspect afterThrowingMethod() called.....");
    }
*/
    // 대상 객체의 메서드 실행 전, 후 또는 익셉션이 발생한 시점에 공통 기능 실행
    /*
            ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드를 호출할 때 사용한다.
            proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출한다.

            ---- ProceedingJoinPoint 인터페이스의 제공 메서드
            Signature getSignature( ) : 호출되는 메서드에 대한 정보를 구한다.
            Object getTarget( ) : 대상 객체를 구한다.
            Object[ ] getArgs( ) : 파라미터 목록을 구한다.

            -----org.aspectj.lang.Signature 인터페이스는 다음 메서드를 정의하고 있다.
            String getName : 메서드 이름을 구한다.
            String toLongString( ) : 메서드를 완전하게 표한한 문장을 구한다.
            String toShortString( ) : 메서드를 축약한 문장을 구한다.
        */
    @Around("onPointcut()")
    public Object onAroundHandler(ProceedingJoinPoint pjp) throws Throwable {

     //   logger.info(pjp.getSignature().getName()); // aop 가 갈려있는 메소드
     //   logger.info(pjp.getSignature().toLongString());
     //   logger.info(pjp.getSignature().toShortString());

    //    logger.info(pjp.getTarget().getClass().getSimpleName()); // aop 가 걸려있는 메소드의 클래스
    //    logger.info(Arrays.toString(pjp.getArgs()));


        logger.info("=============== LogAspect onAroundHandler before !");
        Object result = pjp.proceed();
        logger.info("=============== LogAspect onAroundHandler after !");
        logger.info("LogAspect : " + result.toString());
        return result;
    }
}
