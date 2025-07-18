package com.aiolos.common.web.servlet;

import com.aiolos.common.enums.GatewayHeaderEnum;
import com.aiolos.common.model.ContextInfo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ContextInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIdStr = request.getHeader(GatewayHeaderEnum.USER_LOGIN_ID.getHeaderName());
        // 可能是白名单的url
        if (StringUtils.isBlank(userIdStr)) {
            return true;
        }
        boolean isAnonymous = "true".equals(request.getHeader(GatewayHeaderEnum.IS_ANONYMOUS.getHeaderName()));
        ContextInfo.setUserId(Long.parseLong(userIdStr));
        ContextInfo.setAnonymous(isAnonymous);

        if (!isAnonymous) {
            String userJson = request.getHeader(GatewayHeaderEnum.USER_INFO_JSON.getHeaderName());
            if (StringUtils.isNotBlank(userJson)) {

                byte[] decodeBytes = Base64.getDecoder().decode(userJson);
                JSONObject userJsonObj = JSON.parseObject(new String(decodeBytes, StandardCharsets.UTF_8));
                ContextInfo.setNickName((String) userJsonObj.get("nickName"));
                ContextInfo.setAvatar((String) userJsonObj.get("avatar"));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContextInfo.destroy();
    }
}
