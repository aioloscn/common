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

public class ContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIdStr = request.getHeader(GatewayHeaderEnum.USER_LOGIN_ID.getHeaderName());
        // 可能是白名单的url
        if (StringUtils.isBlank(userIdStr)) {
            return true;
        }
        ContextInfo.setUserId(Long.valueOf(userIdStr));
        String userJson = request.getHeader(GatewayHeaderEnum.USER_INFO_JSON.getHeaderName());
        if (StringUtils.isNotBlank(userJson)) {
            JSONObject userJsonObj = JSON.parseObject(userJson);
            ContextInfo.setNickName((String) userJsonObj.get("nickname"));
            ContextInfo.setAvatar((String) userJsonObj.get("avatar"));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContextInfo.destroy();
    }
}
