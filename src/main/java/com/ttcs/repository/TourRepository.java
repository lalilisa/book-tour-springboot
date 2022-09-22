package com.ttcs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>{
	
	@Query("SELECT a FROM Tour a WHERE a.region LIKE ?1 AND a.category.cateId=?2")
	public Page<Tour> findByRegionAndCategory(String region, Integer cateid, Pageable pageable);
	
	public Page<Tour> findByStartLocLike(String startLoc, Pageable pageable);
	
	@Query("SELECT a FROM Tour a WHERE a.category.cateId=?1")
	public Page<Tour> findByCateId(Integer cateid, Pageable pageable);
	
	public Page<Tour> findByRegionLike(String region, Pageable pageable);
	
	@Query("SELECT t FROM Tour t WHERE t.tourName LIKE CONCAT('%',UPPER(:key),'%')")
	public List<Tour> findByKey(@Param("key") String key);
}
