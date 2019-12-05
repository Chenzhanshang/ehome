package com.zyct.ehome.config.weixin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务端微信工具
 * @author JGZ
 * @Classname WxTools
 * @Date 2019/7/23 14:58
 * @Email 1945282561@qq.com
 */
@Component
public class WxTools {

    @Autowired
    private AppIdAndAppSecret appIdAndAppSecret;

    /**
     *  获取openid和session_key
     * @param code 用户登陆码
     * @return OpenIdAndSessionKey
     */
    public  OpenIdAndSessionKey getOpenidAndSessionkey(String code){
        //创建请求实体类
        RestTemplate restTemplate = new RestTemplate();
        //封装数据
        Map<String,String> map = new HashMap<>();
        String url = appIdAndAppSecret.getJsCode2Session() + "?appid=" + appIdAndAppSecret.getAppId() +
                "&secret=" + appIdAndAppSecret.getAppSecret() +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        String openidAndSessionKey = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        OpenIdAndSessionKey json = gson.fromJson(openidAndSessionKey, OpenIdAndSessionKey.class);
        return json;
    }


}
