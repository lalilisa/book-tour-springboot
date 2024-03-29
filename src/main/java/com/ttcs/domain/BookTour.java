package com.ttcs.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="BookTours")
public class BookTour implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	@ManyToOne
	@JoinColumn(name="tourId")
	private Tour tour;
	@ManyToOne
	@JoinColumn(name="accId")
	private Account account;
	@Column(nullable = false)
	private Date dayStart;
	@Column(nullable = false)
	private int quantity;
	@Column()
	private String payment;
	@Column(nullable = false)
	private Date datebook;
}
