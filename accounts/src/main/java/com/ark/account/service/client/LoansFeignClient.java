package com.ark.account.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ark.account.dto.Customer;
import com.ark.account.dto.Loans;


@FeignClient("Loans")
public interface LoansFeignClient {
	@RequestMapping(method = RequestMethod.POST,value ="myLoans", consumes = "application/json")
	List<Loans> getLoansDetails(@RequestBody Customer customer);
}
