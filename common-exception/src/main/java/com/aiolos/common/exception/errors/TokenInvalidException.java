package com.aiolos.common.exception.errors;

import com.aiolos.common.enums.errors.CommonError;

/**
 * @author Aiolos
 * @date 2021/6/24 8:30 下午
 */
public class TokenInvalidException extends CustomizedException {
    public TokenInvalidException(CommonError commonError) {
        super(commonError);
    }

    public TokenInvalidException(CommonError commonError, String errMsg) {
        super(commonError, errMsg);
    }
}
