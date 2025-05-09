package com.aiolos.common.model;

import com.aiolos.common.model.response.AccessContext;

public class ContextInfo {

    private static final ThreadLocal<AccessContext> context = new InheritableThreadLocal<>();
    
    public static void setUserId(Long userId) {
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
    
    public static void destroy() {
        context.remove();
    }
}
