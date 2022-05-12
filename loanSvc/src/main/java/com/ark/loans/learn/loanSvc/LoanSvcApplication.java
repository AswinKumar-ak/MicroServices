package com.ark.loans.learn.loanSvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScan("com.ark.loans.learn")
@EnableJpaRepositories("com.ark.loans.learn.loanRepo")
@EntityScan("com.ark.loans.learn.Model")
public class LoanSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanSvcApplication.class, args);
	}

}
