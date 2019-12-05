package com.zyct.ehome.utils;

/**
 * 成功信息
 * @author JGZ
 * CreateTime 2019/12/5 9:57
 * Email 1945282561@qq.com
 */
public enum SuccessEnum {

    /**
     * 成功信息
     */
    S_LOGINSUCCESS("0","登录成功"),
    S_LOGINED("0","用户已登录，无需重复登录");



    /**
     * 成功代码
     */
    private String successCode;
    /**
     * 成功信息
     */
    private String successMsg;

    SuccessEnum(String successCode, String successMsg) {
        this.successCode = successCode;
        this.successMsg = successMsg;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public String getSuccessMsg() {
        return successMsg;
    }
}
