/**
 * @Title JEELP轻量级开发平台启动类
 * @<Description  JEELP轻量级开发平台启动类，完成平台启动时业务处理
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform;

import com.jeelp.platform.common.utils.SpringContextHolder;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
@MapperScan({"com.jeelp.platform.**.mapper"})
@ServletComponentScan
public class JeelpApplication {
    /**
     * @Title JEELP启动app的main函数
     * @param args parameter
     * @author JEELP
     * @since 2020.05.01
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JeelpApplication.class, args);
        JeelpApplication app = context.getBean(JeelpApplication.class);
        app.execStartup();//启动时的业务预处理。
    }

    /**
     * @Title 以静态变量保存Spring ApplicationContext,可在任何代码任何地方任何时候取出ApplicaitonContext
     * @author JEELP
     * @since 2020.05.01
     */

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }


    /**
     * @Title 启动时的处理的业务
     * @author JEELP
     * @since 2020.05.01
     */
    private void execStartup() {
        System.out.println("=============================================");
        System.out.println("=JeelpApplication工程启动，业务预处理...开始=");
        System.out.println("=============================================");
    	// 缓存系统参数
    	SysParamService SysParamService = (SpringContextHolder.getBean(SysParamService.class));
    	SysParamService.reloadRedis();

        System.out.println("=============================================");
        System.out.println("=JeelpApplication工程启动，业务预处理...完成=");
        System.out.println("=============================================");
    }
}