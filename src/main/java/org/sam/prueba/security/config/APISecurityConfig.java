package org.sam.prueba.security.config;

import org.sam.prueba.security.authentication.ApiKeyAuthenticationFilter;
import org.sam.prueba.security.authentication.ApiKeyAuthenticationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${superheroes.apikey.name}")
    private String apiKeyHeaderName;

    @Value("${superheroes.apikey.value}")
    private String validApiKey;

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        ApiKeyAuthenticationFilter filter = new ApiKeyAuthenticationFilter(apiKeyHeaderName);
        filter.setAuthenticationManager(
            new ApiKeyAuthenticationManager(validApiKey)    
        );

        security
            .antMatcher("/api/**")
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
