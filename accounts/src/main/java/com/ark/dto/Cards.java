package com.ark.dto;

import java.sql.Date;

import javax.persistence.Column;

import lombok.Data;
@Data
public class Cards {
	
	private int cardId;

	private int customerId;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int availableAmount;

	private Date createDt;

}
