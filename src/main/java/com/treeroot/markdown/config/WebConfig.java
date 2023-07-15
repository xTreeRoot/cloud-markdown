package com.treeroot.markdown.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xugenyin
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.markdown}")
    public String markdownFileRootPath;
    @Value("${file.image}")
    public String imageFileRootPath;

    /**
     * 资源映射:把请求的/markdown/** 映射到该文件根路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/markdown/**").addResourceLocations("file:" + markdownFileRootPath);
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + imageFileRootPath);
    }

    /**
     * 跨域请求
     * @param registry  跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }






}
