package org.sam.prueba.superheroes.config;

import java.util.Collections;
import java.util.List;
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
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
public class SwaggerConfig {

    public static final String TAG_1 = "Controlador Superhéroes";

    private static Predicate<String> postPaths() {
        return PathSelectors.regex("/api/superheroes.*");
    }

    private static List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] scopes = {
            new AuthorizationScope("global", "accessEverything")
        };
        return Collections.singletonList(new SecurityReference("APIKey", scopes));
    }

    private static SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(
                    oc -> postPaths().test(oc.requestMappingPattern())
                ).build();
    }
        
    private ApiKey apiKey() {
        return new ApiKey("APIKey", "APIKey", "header");
    }
    
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(""))
                .paths(postPaths())
                .build()
                .tags(new Tag(TAG_1, "Operaciones típicas consulta/alta/baja/modificación"))
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
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
