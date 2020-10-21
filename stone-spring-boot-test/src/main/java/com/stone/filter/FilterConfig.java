package com.stone.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@Slf4j
//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBean() {
        log.info("registrationBean..............");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
