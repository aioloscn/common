package com.aiolos.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultUtil {

    public static String processErrorString(BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {

            sb.append(error.getDefaultMessage() + ",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
