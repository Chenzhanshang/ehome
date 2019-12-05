package com.zyct.ehome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyct.ehome.config.weixin.OpenIdAndSessionKey;
import com.zyct.ehome.config.weixin.RawData;
import com.zyct.ehome.config.weixin.WxTools;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-03 17:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService UserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @Autowired
    WxTools wxTools;


    @RequestMapping(value = "/userLogin")
    public ResponseEntity<Map<String, String>> userLogin(String code, String rawData, String signature, String encrypteData, String iv){
        ObjectMapper mapper = new ObjectMapper();
        RawData data = null;
        OpenIdAndSessionKey openidAndSessionkey = null;
        String openid = null;
        String sessionKey = null;

        try {
            //获取数据
            if (rawData != null && !"".equals(rawData)){
                data = mapper.readValue(rawData,RawData.class);
            }
            //调用工具获取openid和sessionkey
            openidAndSessionkey = wxTools.getOpenidAndSessionkey(code);
            openid = openidAndSessionkey.getOpenid();
            sessionKey = openidAndSessionkey.getSession_key();
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println(openid);
        System.out.println(sessionKey);
        //插入用户
        String userId = UserService.insertUser(openid);

        //缓存openid, sessionKey, userId
        redisCache(openid,sessionKey,userId);

        //返回数据
        Map<String,String> map = new HashMap<String,String>();
        map.put("status","1");
        map.put("userId",userId);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    /**
     * 缓存openid sessionKey userId
     * @param openid
     * @param sessionKey
     * @param userId
     */
    private void redisCache(String openid,String sessionKey,String userId){
        HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
        //根据openid查询用户的userId
        String userIdRedis = (String) ops.get("WEXIN_USER_OPENID_USERID", openid);
        if(userIdRedis != null && !"".equals(userIdRedis)){
            //如果存在
            ops.delete("WEIXIN_USER_OPENID_USERID",openid);
            ops.delete("WEIXIN_USER_USERID_OPENID", userIdRedis);
            ops.delete("WEIXIN_USER_USERID_SESSIONKEY",userIdRedis);
        }
        //缓存新的
        ops.put("WEIXIN_USER_OPENID_USERID",openid,userId);
        ops.put("WEIXIN_USER_USERID_OPENID",userId,openid);
        ops.put("WEIXIN_USER_USERID_SESSIONKEY",userId,sessionKey);

    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


}
