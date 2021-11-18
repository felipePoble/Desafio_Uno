package com.fjpp.desafio;

import com.fasterxml.classmate.TypeResolver;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@PropertySource("classpath:config.properties")
public class DesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Autowired
    private TypeResolver typeResolver;

    @Value("${rutaGdd}")
    private String rutaGdd;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(List.class, LocalDate.class),
                                typeResolver.resolve(List.class, Date.class),
                                Ordered.HIGHEST_PRECEDENCE
                        )
                )
                .directModelSubstitute(LocalDate.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Solución Desafio Previred",
                "Una solución al desafio de PreviRed para el proceso de postulación.",
                "desafio 1",
                "La aplicación es de uso libre.",
                new Contact("Felipe Poblete", "", "felipejavierpoblete@gmail.com"),
                "Sin licencia", "", Collections.emptyList());
    }
}
