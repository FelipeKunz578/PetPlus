package com.example.PetShop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = uploadPath != null ? uploadPath : "";
        if (!path.endsWith("/")) path = path + "/";
        // Serve files under /uploads/** from the filesystem path configured in upload.path
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + path);
    }
}
