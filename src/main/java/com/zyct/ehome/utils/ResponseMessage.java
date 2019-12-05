package com.zyct.ehome.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前端的消息
 * @author JGZ
 * @Classname ResponseMessage
 * @Date 2019/8/8 14:34
 * @Email 1945282561@qq.com
 */
public class ResponseMessage implements Serializable {

    private String status ;
    private String msg ;
    private Map<String,Object> data = new HashMap<String,Object>();


    public ResponseMessage() {
    }

    public ResponseMessage(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
