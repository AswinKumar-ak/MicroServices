package com.ark.account.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ark.account.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>{

	List<Accounts> findByCustomerId(int customerId);
}
