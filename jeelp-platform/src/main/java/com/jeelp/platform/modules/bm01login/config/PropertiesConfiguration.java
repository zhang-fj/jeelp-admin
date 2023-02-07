/**
 * @Title 配置文件转换Pojo类的统一配置类
 * @<Description  配置文件转换Pojo类的统一配置类，目前只有一个jwt，如果有其他的yml转成配置的，也都在这里追加即可实现集中管理
 * @<Copyright  JEELP  Copyright (c) 2013
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform.modules.bm01login.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeelp.platform.modules.bm01login.config.bean.SecurityProperties;

@Configuration
public class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

}
