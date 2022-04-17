package org.sam.prueba.common.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogearTiempoEjecucionAspect {

    private static final String POINTCUT_METHOD1
        = "@annotation(org.sam.prueba.common.aspect.LogearTiempoEjecucion)";

    private final static Logger logger
        = Logger.getLogger("org.sam.prueba.common.aspect.LogearTiempoEjecucionAspect");
    
    @Around(POINTCUT_METHOD1)
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        logger.log(Level.INFO,
            ()-> joinPoint.getSignature() + " ejecutado en " + executionTime + " ms"
        );

        return proceed;
    }
}
