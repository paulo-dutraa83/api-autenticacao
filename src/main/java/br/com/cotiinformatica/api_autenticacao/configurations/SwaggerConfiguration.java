package br.com.cotiinformatica.api_autenticacao.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticação")
                        .version("1.0.0")
                        .description("""
                                API REST para gerenciamento e autenticação de usuários,
                                desenvolvida com Spring Boot.

                                Projeto destinado a centralizar a autenticação
                                dos demais sistemas da aplicação.

                                Tecnologias utilizadas:
                                - Spring Web
                                - Spring Data JPA
                                - MySQL
                                - Docker
                                """)
                        .contact(new Contact()
                                .name("Professor Sergio Mendes - COTI Informática")));
    }
}