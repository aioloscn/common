package com.aiolos.common.enums.errors;

import com.aiolos.common.enums.base.BaseEnum;

/**
 * @author Aiolos
 * @date 2020/9/22 2:51 下午
 */
public interface CommonError extends BaseEnum {

    Integer getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
