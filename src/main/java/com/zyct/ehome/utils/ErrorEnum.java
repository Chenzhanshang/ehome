package com.zyct.ehome.utils;

/**
 * 错误信息
 * @author JGZ
 * CreateTime 2019/12/5 9:57
 * Email 1945282561@qq.com
 */
public enum ErrorEnum {

    /**
     * 错误信息
     */
    E_UNKNOWACCOUNT("-1","用户不存在"),
    E_PASSWORDERROR("-2","密码错误"),
    E_UNAUTHORIZED("-3","权限不足"),
    E_UNAUTHENTICATED("-4","用户未登录");


    /**
     * 错误代码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
