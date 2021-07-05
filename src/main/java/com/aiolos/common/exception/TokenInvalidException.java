package com.aiolos.common.exception;

/**
 * @author Aiolos
 * @date 2021/6/27 5:44 下午
 */
public class TokenInvalidException extends CustomizeException {
    public TokenInvalidException(CommonError commonError) {
        super(commonError);
    }

    public TokenInvalidException(CommonError commonError, String errMsg) {
        super(commonError, errMsg);
    }
}
