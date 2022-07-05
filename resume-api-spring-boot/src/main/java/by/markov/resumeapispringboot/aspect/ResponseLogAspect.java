package by.markov.resumeapispringboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class ResponseLogAspect {

    @Pointcut("execution(* by.markov.resumeapispringboot.controller.EmployeeController.*(..))")
    public void loggingWithAnnotation() {
    }

    @Around("loggingWithAnnotation()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ob = joinPoint.proceed();
        log.info("\nExecution of method with return type: " + joinPoint.getSignature().toString() + " With arguments: " + Arrays.toString(joinPoint.getArgs()) +
                "\nResult:");
        if (ob instanceof Page) {
            Page resumePage = (Page) ob;
            resumePage.stream().forEach(e -> log.info(e.toString()));
        } else {
            log.info(ob.toString());
        }
        return ob;
    }
}
