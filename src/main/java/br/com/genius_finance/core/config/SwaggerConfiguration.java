package br.com.genius_finance.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

    private static Contact getContact() {
        var contact = new Contact();
        contact.setEmail("email");
        contact.setName("name");
        contact.setUrl("www.site.com.br");
        return contact;
    }

    private static Server getServer() {
        var server = new Server();
        server.setUrl("http://localhost:8081/");
        server.setDescription("Localhost");
        return server;
    }

    private static Info getInfo(Contact contact) {
        return new Info()
                .title("name")
                .contact(contact)
                .version("1.0")
                .description("name");
    }

    @Bean
    public OpenAPI openAPI() {

        var server = getServer();
        var contact = getContact();
        var info = getInfo(contact);

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .servers(singletonList(server));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}