package com.aiolos.common.model;

import com.aiolos.common.model.response.AccessContext;
import org.apache.commons.lang3.StringUtils;

public class ContextInfo {

    private static final ThreadLocal<AccessContext> context = new InheritableThreadLocal<>();
    
    public static void setUserId(Long userId) {
        if (userId == null) return;
        if (context.get() == null) {
            context.set(new AccessContext());
        }
        context.get().setUserId(userId);
    }
    
    public static Long getUserId() {
        if (context.get() == null) {
            return null;
        }
        return context.get().getUserId();
    }
    
    public static void setNickName(String nickName) {
        if (StringUtils.isBlank(nickName)) return;
        if (context.get() == null) {
            context.set(new AccessContext());
        }
        context.get().setNickName(nickName);
    }
    
    public static String getNickName() {
        if (context.get() == null) {
            return null;
        }
        return context.get().getNickName();
    }
    
    public static void setAvatar(String avatar) {
        if (StringUtils.isBlank(avatar)) return;
        if (context.get() == null) {
            context.set(new AccessContext());
        }
        context.get().setAvatar(avatar);
    }
    
    public static String getAvatar() {
        if (context.get() == null) {
            return null;
        }
        return context.get().getAvatar();
    }
    
    public static void destroy() {
        context.remove();
    }
}
