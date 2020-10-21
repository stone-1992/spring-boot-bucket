package com.stone.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/28 17:13
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义切面
     * 匹配 com.stone.controller 包下的所有方法
     * @Pointcut("@annotation(com.stone.face.AspectLog)") -- 所有标识了 AspectLog 注解的方法
     */
    @Pointcut("execution(public * com.stone.controller.*.*(..))")
    public void aspectLog() {

    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("aspectLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("收到请求...........");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}", request.getMethod());
        log.info("IP : {}", request.getRemoteAddr());
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "aspectLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("aspectLog()")
    public void throwss(JoinPoint jp) {
        log.info("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @throws Throwable
     */
    @After("aspectLog()")
    public void after(JoinPoint joinPoint) throws Throwable {
        log.info("方法最后执行.............");
    }

    /**
     * 环绕通知,环绕增强，相当于 MethodInterceptor
     *
     * @param thisJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("aspectLog()")
    public Object around(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        log.info("方法环绕 start.......");
        Object result = thisJoinPoint.proceed();
        log.info("方法环绕 proceed, 结果是：{}", result);
        //执行方法名称
        String taskName = thisJoinPoint.getSignature()
                .toString().substring(
                        thisJoinPoint.getSignature()
                                .toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        long time = System.currentTimeMillis();
        log.info("method:{} run :{} ms", taskName, (System.currentTimeMillis() - time));
        return result;
    }
}
