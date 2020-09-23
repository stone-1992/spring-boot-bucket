package com.stone.face;

import com.stone.config.AspectLogAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 日志记录 注解
 * @date 2020/9/18 15:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AspectLogAutoConfigure.class})
public @interface AspectLog {
    String desc() default "无信息";
}
