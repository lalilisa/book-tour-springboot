package com.ttcs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Accounts")
public class Account implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accId;
	@Column(length=255, nullable=false, unique = true)
	private String userName;
	@Column(length=255, nullable=false)
	private String password;
	@Column()
	private String name;
	@Column(length = 100)
	private String email;
	@Column(nullable = false)
	private int role;
	@Column()
	private String avata;
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<BookTour> listbooks;
	
}
