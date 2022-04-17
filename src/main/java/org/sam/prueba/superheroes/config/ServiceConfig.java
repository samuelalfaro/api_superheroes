package org.sam.prueba.superheroes.config;

import org.sam.prueba.superheroes.service.SuperheroesService;
import org.sam.prueba.superheroes.service.imp.SuperheroesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ServiceConfig {

    @Bean
    SuperheroesService superheroesService(@Autowired NamedParameterJdbcTemplate namedJdbc) {
        return new SuperheroesServiceImp(namedJdbc);
    }

}
