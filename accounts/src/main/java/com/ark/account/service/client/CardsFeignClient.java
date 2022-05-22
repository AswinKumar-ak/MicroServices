package com.ark.account.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ark.account.dto.Cards;
import com.ark.account.dto.Customer;


@FeignClient("cards")
public interface CardsFeignClient {
	@RequestMapping(method = RequestMethod.POST,value ="mycards", consumes = "application/json")
	List<Cards> getCardsDetails(@RequestHeader("eazybank-correlation-id") String correlationid,@RequestBody Customer customer);

}
