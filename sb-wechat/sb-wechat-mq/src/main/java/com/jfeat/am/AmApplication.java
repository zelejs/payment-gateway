package com.jfeat.am;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringBoot方式启动类
 *
 * @author Admin
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
@EnableScheduling
public class AmApplication extends WebMvcConfigurerAdapter{

    protected final static Logger logger = LoggerFactory.getLogger(com.jfeat.AmApplication.class);

    //@Autowired
    //AmProperties amProperties;

    /**
     * 增加swagger的支持
     */
  /*  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(amProperties.getSwaggerOpen()){
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }*/

    public static void main(String[] args) {
        SpringApplication.run(com.jfeat.AmApplication.class, args);
        logger.info("Wechat is success!");
    }
}
