package com.ttcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.BookTour;

@Repository
public interface BookTourRepository extends JpaRepository<BookTour, Integer>{
	
	@Query("SELECT t FROM BookTour t WHERE t.account.accId=?1")
	List<BookTour> findByIdAccount(Integer id);
	
	@Query("SELECT t FROM BookTour t WHERE t.account.userName LIKE CONCAT('%',UPPER(:key),'%')")
	List<BookTour> findByKey(String key);
	
	@Query("SELECT t FROM BookTour t WHERE t.account.userName=?1")
	List<BookTour> findByUsername(String username);
	
}
