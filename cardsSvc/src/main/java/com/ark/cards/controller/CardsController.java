package com.ark.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ark.cards.config.CardsConfig;
import com.ark.cards.config.CardsProperties;
import com.ark.cards.model.Cards;
import com.ark.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CardsController {


	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	CardsConfig cardsConfig;

	@PostMapping("/mycards")
	public List<Cards> getAccountsDetails(@RequestHeader("eazybank-correlation-id") String correlationid,@RequestBody Cards card) {

		List<Cards> cards = cardsRepository.findByCustomerId(card.getCustomerId());
		if (cards != null)
			return cards;
		else
			return null;

	}
	@GetMapping("/cards/getCardsConfig")
	public String getpropertiesDetails() throws JsonProcessingException {
		com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		CardsProperties properties = new CardsProperties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String json = objectWriter.writeValueAsString(properties);
		return json;
	}


}
