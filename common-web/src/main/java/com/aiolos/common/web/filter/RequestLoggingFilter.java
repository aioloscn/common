package com.aiolos.common.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestUrl = httpRequest.getRequestURL().toString();
        String method = httpRequest.getMethod();
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = httpRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            params.put(paramName, httpRequest.getParameter(paramName));
        }

        log.info("Request Start - URL: {}, Method: {}, Params: {}", requestUrl, method, params);
        try {
            // 继续处理请求
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error("Exception occurred while processing request: {}", e.getMessage(), e);
            throw e;
        } finally {
            // 记录请求耗时
            long elapsedTime = System.currentTimeMillis() - start;
            // 记录响应状态码
            int statusCode = ((HttpServletResponse) servletResponse).getStatus();
            log.info("Request End - Status Code: {}, Elapsed Time: {}ms", statusCode, elapsedTime);
        }
    }
}
