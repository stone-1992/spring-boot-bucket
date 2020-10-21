package com.stone.filter;

import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Component 注入 IOC 容器
 */
@Slf4j
@Component
@Order(value = 2)
public class MyFilter2 implements Filter {

    /**
     * filter 初始化的时候调用，即 web 容器启动的时候 (springBoot 项目启动时)
     * web 容器启动时根据 web.xml 文件，依次加载 ServletContext --> Listener --> Filter --> Servlet
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter2 init .................... ");
    }

    /**
     * Filter 执行功能，可以对 request, response 和 chain （是否放行） 进行操作
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter2 doFilter...................");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = request.getRequestURL();
        log.info("Filter2............{}", requestURL);
        if (requestURL.toString().contains("demo")) {
            sendError(response);
            return;

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * filter 在服务器正常关闭等情况下调用
     */
    @Override
    public void destroy() {
        log.info("Filter2 destroy...............");
    }

    /**
     * 自定义返回格式
     *
     * @param response
     */
    @SneakyThrows
    private void sendError(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(403);
        Map<String, Object> map = new HashMap<>(4);
        // 1 成功, 0 失败
        map.put("code", 0);
        map.put("result", false);
        map.put("resultDesc", "参数异常");
        response.getWriter().write(JSONUtil.toJsonStr(map));
    }
}
