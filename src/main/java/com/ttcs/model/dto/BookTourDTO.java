package com.ttcs.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTourDTO {
	private int bookId;
	private int tourId;
	private String usernameacc;
	private Date dayStart;
	private int quantity;
	private String payment;
	private Date bookdate;
}
