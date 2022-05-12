/**
 * 
 */
package com.ark.loans.learn.loanCtrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ark.loans.learn.Model.Loans;
import com.ark.loans.learn.config.LoansConfig;
import com.ark.loans.learn.config.LoansProperties;
import com.ark.loans.learn.dto.Customer;
import com.ark.loans.learn.loanRepo.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author MGSCHN-ASWIN
 *
 */
@RestController
public class LoansController {
	@Autowired
	private LoansRepository loansRepository;
	
	@Autowired
	LoansConfig loansConfig;
	
	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		System.out.println("Invoking Loans Microservice");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}
	@GetMapping("/loans/getloansConfig")
	public String getpropertiesDetails() throws JsonProcessingException {
		com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		LoansProperties properties = new LoansProperties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
				loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		String json = objectWriter.writeValueAsString(properties);
		return json;
	}
}
