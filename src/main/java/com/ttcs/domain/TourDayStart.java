package com.ttcs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttcs.domain.compositeid.TourDayStartId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(TourDayStartId.class)
@Table(name="TourDaysStart")
public class TourDayStart implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "tourId")
	private Tour tour;
	@Id
	@Column(nullable = false)
	private Date dayStart;
	
}
