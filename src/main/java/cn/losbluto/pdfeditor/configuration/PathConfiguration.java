package cn.losbluto.pdfeditor.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author LosBluto
 * @version 1.0.0
 * @ClassName PathConfiguration.java
 * @Description TODO
 * @createTime 2022年07月02日 23:32:00
 */
@Configuration
public class PathConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").
                addResourceLocations("file:" + System.getProperty("user.dir")+ File.separator+"file"+File.separator);
    }
}
