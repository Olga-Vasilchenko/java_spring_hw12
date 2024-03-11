package com.example.Seminar6HomeWork6.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Aspect
@Component
public class MyLoggingAspect {

     private final Logger logger = Logger.getLogger(MyLoggingAspect.class.getName());

    /**
     * Логирование успешного завершения методов, отмеченных аннотацией @TrackUserAction
     * @param joinPoint точка вызова аспекта
     * @param returnedValue данные, возвращаемые методом
     */
    @AfterReturning(value = "@annotation(TrackUserAction)",returning = "returnedValue")
    public void loggingAspect(JoinPoint joinPoint, Object returnedValue){

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();

        logger.info("Вызванный метод: " + methodName);
        logger.info("Класс: " + className);
        logger.info("Метод отработал и вернул: " + returnedValue);

    }

    /**
     * Логирование методов, отмеченных аннотацией @TrackUserAction в случе выбрасывания исключения
     * @param joinPoint точка вызова аспекта
     * @param ex исключение, которое выбросил метод
     */
    @AfterThrowing(value = "@annotation(TrackUserAction)", throwing = "ex")
    public void loggingException(JoinPoint joinPoint, Exception ex){
        System.out.println("Метод " + joinPoint.getSignature().getName()
                + "вернул исключение " + ex.getClass() + " " + ex.getMessage());
    }
}
