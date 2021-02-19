package org.iainuk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1) // Order of aspect execution, -max -> max. Same number = random order
public class TestAspect { // Could be logging / security etc.

    @Pointcut("execution(* org.iainuk.aop.*.*(..))")
    private void allMethods() {}

    @Pointcut("execution(* org.iainuk.aop.*.get*(..))")
    private void getters() {}

    @Pointcut("execution(* org.iainuk.aop.*.set*(..))")
    private void setters() {}

    @Pointcut("allMethods() && !(getters() || setters())")
    private void noGettersOrSetters() {}

    @Before("allMethods()") // Use fully qualified expression path if expressions in own aspect file
    public void addAccountAdvice(JoinPoint joinPoint) {
        System.out.println("=======> BEFORE ALL METHODS");
        System.out.println(joinPoint.getSignature());
    }

    @Before("noGettersOrSetters()")
    public void testNoGetters(JoinPoint joinPoint) { // gives meta data about method called. getSignature/Args() etc.
        System.out.println("=======> ONLY BEFORE NONE GETTERS/SETTERS");
        System.out.println(joinPoint.getSignature());
    }

    @AfterReturning(
            pointcut="execution(* org.iainuk.aop.*.getAccounts(..))",
            returning="result")
    public void afterReturningTest(JoinPoint joinPoint, List<Account> result) {
        // modify resulting list if desired
        System.out.println(result);
        System.out.println("doo doo");
    }

    @AfterThrowing(
            pointcut="execution(* org.iainuk.aop.*.getAccounts(..))",
            throwing="exc")
    public void afterThrowingTest(JoinPoint joinPoint, Throwable exc) {
        // joinPoint.getSignature().toShortString(); see which method failed
        System.out.println(exc);
    }

    @Around("execution(* org.iainuk.aop.*.getAccounts(..))")
    public Object aroundTest(ProceedingJoinPoint jp) throws Throwable {

        System.out.println(jp.getSignature().toShortString());

        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Duration: " + duration / 1000.0 + " seconds");

        return result;

        // Use try/catch block around proceed() to catch exceptions and return default values / or rethrow exc
    }
}
