package org.sam.prueba.superheroes;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@Import({org.sam.prueba.common.config.SpringAspectConfig.class
        ,org.sam.prueba.common.exceptions.ExceptionHelper.class
        ,org.sam.prueba.security.config.APISecurityConfig.class
})
@EnableCaching
@Configuration
public class SuperheroesApplication implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index.html");
    }

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesApplication.class, args);
    }

}
