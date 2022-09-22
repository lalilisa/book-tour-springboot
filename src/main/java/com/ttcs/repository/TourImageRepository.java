package com.ttcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ttcs.domain.TourImage;
import com.ttcs.domain.compositeid.TourImageId;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage, TourImageId>{
	@Query(value="select * from tour_images t where t.tour_id=?1", nativeQuery = true)
	List<TourImage> findByIdTour(Integer id);
	
	@Modifying
	@Query(value = "insert into tour_images (tour_id,url) VALUES (?1,?2)", nativeQuery = true)
	void insert(Integer tourId, String url);
	
	@Modifying
	@Query(value="delete from tour_images where tour_id=?1", nativeQuery = true)
	void deleteByTourId(Integer tourId);
}
