package com.ark.accounts.dto;

import java.util.List;

import com.ark.account.model.Accounts;
import com.ark.cards.model.Cards;
import com.ark.loans.learn.Model.Loans;

import lombok.Data;

@Data
public class CustomerDetails {
	private List<Accounts> accounts;
	private List<Cards> cards;
	private List<Loans> loans;

}
