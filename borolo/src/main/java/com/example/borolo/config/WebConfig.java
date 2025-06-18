package com.example.borolo.config; //이미지 처리 클래스

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("user.dir") + "/uploads/images/";

        registry.addResourceHandler("/uploads/images/**") // URL로 접근할 경로
                .addResourceLocations("file:" + uploadPath); // 실제 이미지 저장 경로
    }
}
