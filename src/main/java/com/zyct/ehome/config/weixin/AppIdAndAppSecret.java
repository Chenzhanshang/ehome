package com.zyct.ehome.config.weixin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 小程序的appid和appSecret
 * @author JGZ
 * @Classname AppIdAndAppSecret
 * @Date 2019/7/26 11:46
 * @Email 1945282561@qq.com
 */
@PropertySource("classpath:wx.properties")
@ConfigurationProperties(prefix = "wx")
@Configuration
@Data
public class AppIdAndAppSecret {
    private String jsCode2Session ;
    private String appId;
    private String appSecret;
}
