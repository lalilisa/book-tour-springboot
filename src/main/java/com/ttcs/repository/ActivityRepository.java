package com.ttcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.Activity;
import com.ttcs.domain.compositeid.ActivityId;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, ActivityId>{
	@Query(value="select * from activities t where t.tour_id=?1", nativeQuery = true)
	List<Activity> findByIdTour(Integer id);
	
	@Modifying
	@Query(value = "insert into activities (tour_id,day,title,act) VALUES (?1,?2,?3,?4)", nativeQuery = true)
	void insert(Integer tourId, Integer day, String title, String act);
	
	@Modifying
	@Query(value="delete from activities where tour_id=?1", nativeQuery = true)
	void deleteByTourId(Integer tourId);
}
