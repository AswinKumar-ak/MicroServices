package com.ark.accounts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ark.accounts.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>{

	List<Accounts> findByCustomerId(int customerId);
}
