package com.stone.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 日志记录配置类
 * @date 2020/9/18 14:56
 */

@Configuration
@ConditionalOnProperty(prefix = "aspect.log", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AspectLogProperties.class)
@Aspect
public class AspectLogAutoConfigure {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.stone.face.AspectLog)")
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
        logger.info("收到请求...........");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL : {}", request.getRequestURL().toString());
        logger.info("HTTP_METHOD : {}", request.getMethod());
        logger.info("IP : {}", request.getRemoteAddr());
        logger.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "aspectLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("aspectLog()")
    public void throwss(JoinPoint jp) {
        logger.info("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @throws Throwable
     */
    @After("aspectLog()")
    public void after(JoinPoint joinPoint) throws Throwable {
        logger.info("方法最后执行.............");
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
        logger.info("方法环绕 start.......");
        Object result = thisJoinPoint.proceed();
        logger.info("方法环绕 proceed, 结果是：{}", result);
        //执行方法名称
        String taskName = thisJoinPoint.getSignature()
                .toString().substring(
                        thisJoinPoint.getSignature()
                                .toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        long time = System.currentTimeMillis();
        logger.info("method:{} run :{} ms", taskName, (System.currentTimeMillis() - time));
        return result;
    }
}



