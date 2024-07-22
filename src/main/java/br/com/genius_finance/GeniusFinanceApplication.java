package br.com.genius_finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = {
                "br.com.genius_finance.repository",
                "br.com.genius_finance.repository.*"
        }
)
public class GeniusFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeniusFinanceApplication.class, args);
    }

}
