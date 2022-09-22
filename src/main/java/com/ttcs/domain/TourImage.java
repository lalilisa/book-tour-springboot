package com.ttcs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttcs.domain.compositeid.TourImageId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(TourImageId.class)
@Table(name="TourImages")
public class TourImage implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "tourId")
	private Tour tour; 
	@Id
	@Column(nullable = false)
	private String url;
	
}
