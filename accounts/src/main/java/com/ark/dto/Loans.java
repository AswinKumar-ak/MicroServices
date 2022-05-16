package com.ark.dto;

import java.util.Date;


import lombok.Data;
@Data
public class Loans {
	
	
	private int loanNumber;

	private int customerId;

	private Date startDt;

	private String loanType;

	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;

	private String createDt;

}
