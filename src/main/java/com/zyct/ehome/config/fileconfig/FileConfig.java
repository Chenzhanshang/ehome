package com.zyct.ehome.config.fileconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 16:35
 */
@PropertySource("classpath:file.properties")
@Configuration
public class FileConfig implements WebMvcConfigurer {

    /**
     * 实际文件存储位置
     */
    @Value("${filepath}")
    private String filepath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/file/**").addResourceLocations("file:"+filepath);

    }
}
