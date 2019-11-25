package org.theenergymashuplab.cts.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringSecurityConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index.html");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/hello").setViewName("hello.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/signup").setViewName("signup.html");
    }

}