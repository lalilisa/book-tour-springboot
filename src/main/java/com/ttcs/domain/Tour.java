package com.ttcs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Tours")
public class Tour implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tourId;
	@Column()
	private String tourName;
	@Column(nullable = false)
	private Long tourCost;
	@Column()
	private String tourDescription;
	@Column()
	private String startLoc;
	@Column(nullable = false)
	private int sale;
	@Column()
	private String region;
	@Column(nullable = false)
	private int days;
	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
	private List<TourDayStart> listDayStarts = new ArrayList<>();
	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
	private List<TourImage> listimgs = new ArrayList<>();
	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
	private List<Activity> listacts = new ArrayList<>();
	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
	private List<BookTour> listbooks = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="cateId")
	private Category category;
	
	
}
