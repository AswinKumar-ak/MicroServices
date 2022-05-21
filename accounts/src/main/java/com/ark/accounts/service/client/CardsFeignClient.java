package com.ark.accounts.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ark.accounts.dto.Cards;
import com.ark.accounts.dto.Customer;


@FeignClient("Cards")
public interface CardsFeignClient {
	@RequestMapping(method = RequestMethod.POST,value ="mycards", consumes = "application/json")
	List<Cards> getCardsDetails(@RequestBody Customer customer);

}
