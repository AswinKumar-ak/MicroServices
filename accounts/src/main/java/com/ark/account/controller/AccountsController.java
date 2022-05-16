/**
 * 
 */
package com.ark.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ark.account.repository.AccountsRepository;
import com.ark.account.service.client.CardsFeignClient;
import com.ark.account.service.client.LoansFeignClient;
import com.ark.config.AccountsConfig;
import com.ark.config.AccountsProperties;
import com.ark.dto.Customer;
import com.ark.dto.CustomerDetails;
import com.ark.dto.Loans;
import com.ark.model.Accounts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @author MGSCHN-ASWIN
 *
 */
@RestController
public class AccountsController {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	AccountsConfig accountsConfig;

	@Autowired
	LoansFeignClient loansFeignClient;

	@Autowired
	CardsFeignClient cardsFeignClient;

	@PostMapping("/myAccount")
	public List<Accounts> getAccountsDetails(@RequestBody Customer customer) {

		List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null)
			return accounts;
		else
			return null;

	}

	@GetMapping("/accounts/getAccountsConfig")
	public String getpropertiesDetails() throws JsonProcessingException {
		com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		AccountsProperties properties = new AccountsProperties(accountsConfig.getMsg(),
				accountsConfig.getBuildVersion(), accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String json = objectWriter.writeValueAsString(properties);
		return json;
	}

	@PostMapping("/myCustomerDeatils")
	/*
	 * @CircuitBreaker(name = "detailsForCustomerSupportApp",fallbackMethod
	 * ="myCustomerDetailsFallBack")
	 */
	@Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallBack")
	public CustomerDetails myCustomerDeatils(@RequestBody Customer customer) {
		List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<com.ark.dto.Loans> loans = loansFeignClient.getLoansDetails(customer);
		List<com.ark.dto.Cards> cards = cardsFeignClient.getCardsDetails(customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		return customerDetails;
	}

	private CustomerDetails myCustomerDetailsFallBack(Customer customer, Throwable t) {
		List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		return customerDetails;

	}

	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, In normal";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Ratelimit invoked";
	}
}
