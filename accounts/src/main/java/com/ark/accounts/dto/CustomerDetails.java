package com.ark.accounts.dto;

import java.util.List;

import com.ark.accounts.model.Accounts;

import lombok.Data;

@Data
public class CustomerDetails {
	private List<Accounts> accounts;
	private List<Cards> cards;
	private List<Loans> loans;

}
