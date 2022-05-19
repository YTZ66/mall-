package com.zty.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //和页面有关的静态目录都放在项目的static目录下，指定静态资源的路径
        // 可以通过配置addResourceHandler和addResourceLocations来读取本地资源文件
        registry.addResourceHandler("/hotel/avatar/**").//添加资源程序:表示文件路径，添加的文件全都放在avatar下
                //添加资源位置:表示要开放的资源，将添加的文件放到这里

                addResourceLocations("file:C:/project/hotel/avatar/");
    }
}
