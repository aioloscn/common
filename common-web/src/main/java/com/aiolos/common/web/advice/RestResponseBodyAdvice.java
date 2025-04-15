package com.aiolos.common.web.advice;

import com.aiolos.common.model.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class RestResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof CommonResponse || isResources(request)) {
            return body;
        }

        CommonResponse commonResponse = CommonResponse.ok();
        if (body == null) {
            return commonResponse;
        }
        commonResponse.setData(body);
        return commonResponse;
    }

    private boolean isResources(ServerHttpRequest request) {
        String path = ((ServletServerHttpRequest) request).getServletRequest().getServletPath();
        return path.contains("swagger") || path.contains("api-docs") || path.contains("actuator") || path.contains("error");
    }
}
