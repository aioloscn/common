package com.aiolos.common.exception.utils;

import com.aiolos.common.enums.errors.CommonError;
import com.aiolos.common.exception.errors.CustomizedException;

public class ExceptionUtil {

    public static void throwException(CommonError commonError) {
        try {
            throw new CustomizedException(commonError);
        } catch (CustomizedException e) {
            sneakyThrow(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
