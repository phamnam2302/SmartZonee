package com.smartzone.qlko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình: Khi gọi link http://localhost:8080/photos/ten_anh.jpg
        // Sẽ tìm file trong thư mục "uploads" nằm ở thư mục gốc dự án
        registry.addResourceHandler("/photos/**")
                .addResourceLocations("file:uploads/");
    }
}