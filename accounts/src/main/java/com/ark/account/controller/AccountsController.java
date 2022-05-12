/**
 * 
 */
package com.ark.account.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ark.account.config.AccountsConfig;
import com.ark.account.config.AccountsProperties;
import com.ark.account.model.Accounts;
import com.ark.account.repository.AccountsRepository;
import com.ark.accounts.dto.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		AccountsProperties properties = new AccountsProperties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String json = objectWriter.writeValueAsString(properties);
		return json;
	}
}
