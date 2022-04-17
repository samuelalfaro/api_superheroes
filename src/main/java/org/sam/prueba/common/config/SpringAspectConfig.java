package org.sam.prueba.common.config;

import org.sam.prueba.common.aspect.LogearTiempoEjecucionAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringAspectConfig {

    @Bean
    public LogearTiempoEjecucionAspect logearTiempoEjecucionAspect() {
        return new LogearTiempoEjecucionAspect();
    }
}
