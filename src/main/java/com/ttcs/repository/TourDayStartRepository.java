package com.ttcs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.TourDayStart;
import com.ttcs.domain.compositeid.TourDayStartId;

@Repository
public interface TourDayStartRepository extends JpaRepository<TourDayStart, TourDayStartId>{
	@Query(value="select * from tour_days_start t where t.tour_id=?1", nativeQuery = true)
	List<TourDayStart> findByIdTour(Integer id);
	
	@Modifying
	@Query(value = "insert into tour_days_start (tour_id,day_start) VALUES (?1,?2)", nativeQuery = true)
	void insert(Integer tourId, Date day);
	
	@Modifying
	@Query(value="delete from tour_days_start where tour_id=?1", nativeQuery = true)
	void deleteByTourId(Integer tourId);
}
