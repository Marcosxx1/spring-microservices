package com.accounts;

import com.accounts.domain.dto.AccountContactInfo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info =
                @Info(
                        title = "Accounts microservice REST API Documentation",
                        description = "Microservice using REST API",
                        version = "1.0.0",
                        contact = @Contact(name = "Marcos", url = "https://github.com/Marcosxx1")))
@EnableConfigurationProperties(value = {AccountContactInfo.class})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
