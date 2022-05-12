/**
 * 
 */
package com.ark.loans.learn.loanRepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ark.loans.learn.Model.Loans;

/**
 * @author MGSCHN-ASWIN
 *
 */
public interface LoansRepository extends CrudRepository<Loans, Long> {

	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
