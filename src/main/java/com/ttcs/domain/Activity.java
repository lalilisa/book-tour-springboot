package com.ttcs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttcs.domain.compositeid.ActivityId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(ActivityId.class)
@Table(name="Activities")
public class Activity implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "tourId")
	private Tour tour;
	@Id
	@Column(nullable = false)
	private int day;
	@Column(columnDefinition = "nvarchar(1000)")
	private String title;
	@Column(columnDefinition = "ntext not null")
	private String act;
	
}


