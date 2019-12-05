package com.zyct.ehome.config.weixin;

import lombok.Data;

/**
 * 微信用户的非敏感数据
 * @author JGZ
 * @Classname RawData
 * @Date 2019/7/23 15:55
 * @Email 1945282561@qq.com
 */
@Data
public class RawData {
    private String nickName;
    private Integer gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;


}
