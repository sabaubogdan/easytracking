package xyz.vegaone.easytracking.monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerMonitor {

    @Before("execution(* xyz.vegaone.easytracking.controller..*(..))")
    public void logController(JoinPoint joinPoint){
        log.info("Accesing the " + joinPoint + " having the parameter id:" + joinPoint.getArgs());
    }
}
