package com.ttcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	public Account findByUserName(String username);

	public List<Account> findByRole(Integer role);
	
	@Query("SELECT t FROM Account t WHERE t.role=1 AND"
			+ "(t.userName LIKE CONCAT('%',UPPER(:key),'%') OR t.name LIKE CONCAT('%',UPPER(:key),'%'))")
	public List<Account> findByKey(@Param("key") String key);
}
