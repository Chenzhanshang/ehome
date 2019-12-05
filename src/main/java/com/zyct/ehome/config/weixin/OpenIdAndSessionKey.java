package com.zyct.ehome.config.weixin;

/**
 * 微信服务器返回的openid和session_key
 * @author JGZ
 * @Classname OpenIdAndSessionKey
 * @Date 2019/7/23 15:30
 * @Email 1945282561@qq.com
 */
public class OpenIdAndSessionKey {

    private String session_key;
    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "OpenIdAndSessionKey{" +
                "session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
