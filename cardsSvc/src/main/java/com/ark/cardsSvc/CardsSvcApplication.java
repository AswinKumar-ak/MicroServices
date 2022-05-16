package com.ark.cardsSvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScan("com.ark")
@EnableJpaRepositories("com.ark.repository")
@EntityScan("com.ark.model")
public class CardsSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsSvcApplication.class, args);
	}

}
