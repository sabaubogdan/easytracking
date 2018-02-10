package xyz.vegaone.easytracking.monitor;

import javassist.bytecode.stackmap.TypeData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Aspect
@Component
public class ControllerLoggingAspect {

    @Before("execution(* xyz.vegaone.easytracking.controller..*(..))")
    public void logController(JoinPoint joinPoint) {
       Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        log.info("Accesing the " + joinPoint + " having the parameter:" + Arrays.asList(joinPoint.getArgs()));
    }
}
