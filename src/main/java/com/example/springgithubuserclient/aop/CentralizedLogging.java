package com.example.springgithubuserclient.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Aspect
@Slf4j
@Service
public class CentralizedLogging {
    // before
    // after
    // around

    Logger logger = LoggerFactory.getLogger(CentralizedLogging.class);

    // pointcut - location
    @Pointcut(value = "execution(* com.example.springgithubuserclient.api.*.*(..))")
    public void pointCut1(){

    }

    @Pointcut(value = "execution(* com.example.springgithubuserclient.service.*.*(..))")
    public void pointCut2(){

    }

    @Around("pointCut1() || pointCut2()")
    public Object centralizedLogger(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] argumentArray = pjp.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        String stringArgument = objectMapper.writeValueAsString(argumentArray);
        // database ->
        logger.info(Instant.now() + " Class: " + className + " Method: " + methodName + " started execution with arguments: " + stringArgument);
        Object returnedData = pjp.proceed();
        String stringReturnedData = objectMapper.writeValueAsString(returnedData);
        logger.info(Instant.now() + " Class: " + className + " Method: " + methodName + " finished execution with returned: " + stringReturnedData);
        return returnedData;
    }

}
