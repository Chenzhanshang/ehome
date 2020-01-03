package com.zyct.ehome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyct.ehome.config.weixin.OpenIdAndSessionKey;
import com.zyct.ehome.config.weixin.RawData;
import com.zyct.ehome.config.weixin.WxTools;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-03 17:00
 */
@Controller
@PropertySource("classpath:file.properties")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private com.zyct.ehome.service.UserService UserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${responsePath}")
    private String responsePath;


    @Autowired
    WxTools wxTools;


    /**
     * 用于用户登录
     * @param code
     * @param rawData
     * @param signature
     * @param encrypteData
     * @param iv
     * @return
     */
    @RequestMapping(value = "/userLogin")
    public ResponseEntity<Map<String, Object>> userLogin(String code, String rawData, String signature, String encrypteData, String iv){
        ObjectMapper mapper = new ObjectMapper();
        RawData data = null;
        OpenIdAndSessionKey openidAndSessionkey = null;
        String openid = null;
        String sessionKey = null;

        try {
            //获取数据
            if (rawData != null && !"".equals(rawData)){
                data = mapper.readValue(rawData, RawData.class);
            }
            //调用工具获取openid和sessionkey
            openidAndSessionkey = wxTools.getOpenidAndSessionkey(code);
            openid = openidAndSessionkey.getOpenid();
            sessionKey = openidAndSessionkey.getSession_key();
        } catch (IOException e) {
            e.printStackTrace();

        }
        if (openid != null){
            //插入用户
            Owner owner = UserService.insertUser(openid,data);
            String userId = owner.getOwnerId();
            //缓存openid, sessionKey, userId
            redisCache(openid,sessionKey,userId);
            //返回数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("status","1");
            map.put("userId",userId);
            map.put("owner",owner);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }else {
            System.out.println("openId为空");
        }

        return null;

    }

    @RequestMapping("/getUserNewInfo")
    @ResponseBody
    public ResponseMessage getUserNewInfo(@RequestParam("ownerId")String ownerId){
        if (ownerId.equals("")||ownerId == null){
            return new ResponseMessage("-1","ownerId为空");
        }
        Owner owner = UserService.selectUserByOwnerId(ownerId);
        if (owner.getAvatar().indexOf("https://wx.qlogo.cn/")== -1){
            String path = responsePath+"/ehome/file/"+owner.getAvatar();
            owner.setAvatar(path);
        }
        ResponseMessage responseMessage = new ResponseMessage("0","查询成功");
        Map<String,Object> map = new HashMap<>();
        map.put("owner",owner);
        responseMessage.setData(map);
        return responseMessage;
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
