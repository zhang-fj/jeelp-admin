/**
 * @Title JEELP门户网站启动类
 * @<Description  JEELP门户网站启动类，完成JEELP门户网站启动时业务处理
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.protal;

import com.jeelp.platform.common.utils.SpringContextHolder;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@MapperScan({"com.jeelp.**.mapper"})
@ComponentScan({"com.jeelp.platform.**", "com.jeelp.protal.**"})
@ServletComponentScan(basePackages = {"com.jeelp.platform.**","com.jeelp.protal.**"})
public class JeelpPortalApplication {
    /**
     * @Title JEELP启动app的main函数
     * @param args parameter
     * @author JEELP
     * @since 2020.05.01
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JeelpPortalApplication.class, args);
        JeelpPortalApplication app = context.getBean(JeelpPortalApplication.class);
        app.execStartup();//启动时的业务预处理。
    }

    /**
     * @Title 启动时的处理的业务
     * @author JEELP
     * @since 2020.05.01
     */
    private void execStartup() {
        System.out.println("=============================================");
        System.out.println("=JeelpProtalApplication工程启动，业务预处理...开始=");
        System.out.println("=============================================");

    	// 缓存系统参数
    	SysParamService SysParamService = (SpringContextHolder.getBean(SysParamService.class));
    	SysParamService.reloadRedis();

        System.out.println("=============================================");
        System.out.println("=JeelpProtalApplication工程启动，业务预处理...完成=");
        System.out.println("=============================================");
    }
}