package com.ttcs.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
	private int accId;
	private String userName;
	private String password;
	private String name;
	private String email;
	private int role;
	private String avata;
	private List<BookTourDTO> listbooks;
}
