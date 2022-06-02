package by.markov.resumeapispringboot.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getName());



}
