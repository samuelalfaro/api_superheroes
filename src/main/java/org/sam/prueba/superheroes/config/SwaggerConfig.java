package org.sam.prueba.superheroes.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

@Configuration
public class SwaggerConfig {

    public static final String TAG_1 = "Controlador Superhéroes";

    private static Predicate<String> postPaths() {
        return PathSelectors.regex("/api/superheroes.*");
    }

    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(""))
                .paths(postPaths())
                .build()
                .tags(new Tag(TAG_1, "Operaciones típicas consulta/alta/baja/modificación"))
                .apiInfo(buildInfo());
    }

    private ApiInfo buildInfo() {
        return new ApiInfoBuilder().title("Superheroes API")
                .description("API con más hérores que Marvel y DC juntos")
                .contact(new Contact("salfaro", "", "samuelalfaro@gmail.com"))
                .license("GNU General Public License v3.0")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
                .version("1.0")
                .build();
    }

}
