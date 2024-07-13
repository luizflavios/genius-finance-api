package br.com.genius_finance.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        var server = new Server();
        server.setUrl("http://localhost:8080/");
        server.setDescription("Localhost");

        var contact = new Contact();
        contact.setEmail("email");
        contact.setName("name");
        contact.setUrl("www.site.com.br");

        var info = new Info()
                .title("name")
                .contact(contact)
                .version("1.0")
                .description("name");

        return new OpenAPI()
                .info(info)
                .servers(singletonList(server));
    }

}