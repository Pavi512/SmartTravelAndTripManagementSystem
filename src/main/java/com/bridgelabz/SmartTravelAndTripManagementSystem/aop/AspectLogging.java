package com.bridgelabz.SmartTravelAndTripManagementSystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Marks this class as an Aspect
@Aspect

// To create Spring bean
@Component
public class AspectLogging {

    // Creates logger object using SLF4J
    private static final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    // Executes before every service method
    @Before("execution(* com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl..*(..))")
    public void beforeMethod(JoinPoint joinPoint) {

        // Service class name and method name before execution
        logger.info("Method execution started: {}.{}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );
    }

    // Executes around every service method to calculate execution time
    @Around("execution(* com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl..*(..))")
    public Object timeExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        // Stores the start time
        long start = System.currentTimeMillis();

        // Executes the actual service method
        Object result = joinPoint.proceed();

        // Stores the end time
        long end = System.currentTimeMillis();

        // Execution time of the service method
        logger.info("Execution Time: {}.{} = {} ms",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                (end - start)
        );

        return result;
    }

    // Executes after the service method
    @After("execution(* com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl..*(..))")
    public void afterMethod(JoinPoint joinPoint) {

        logger.info("Method execution completed: {}.{}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );
    }

    // Executes after successful method execution
    @AfterReturning(pointcut = "execution(* com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl..*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        logger.info("Method executed successfully: {}.{}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );
    }


    // Executes when a service method throws an exception
    @AfterThrowing(pointcut = "execution(* com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl..*(..))", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {

        // Exception message and service method where it occurred
        logger.error("Exception in {}.{} : {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                exception.getMessage()
        );
    }
}