package com.ttcs.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ttcs.domain.Account;
import com.ttcs.domain.BookTour;
import com.ttcs.model.dto.AccountDTO;
import com.ttcs.model.dto.BookTourDTO;

public class AccountMapper {
	public static AccountDTO toAccountDTO(Account account) {
		AccountDTO tmp = new AccountDTO();
		List<BookTourDTO> list = new ArrayList<>();
		for(BookTour b : account.getListbooks()) {
			list.add(BookTourMapper.toBookTourDTO(b));
		}
		tmp.setAccId(account.getAccId());
		tmp.setUserName(account.getUserName());
		tmp.setPassword(account.getPassword());
		tmp.setName(account.getName());
		tmp.setEmail(account.getEmail());
		tmp.setRole(account.getRole());
		tmp.setAvata(account.getAvata());
		tmp.setListbooks(list);
		return tmp;
	}
	
	public static List<AccountDTO> toListAccountDTOs(List<Account> list){
		List<AccountDTO> tmp = new ArrayList<>();
		for (Account i : list) {
			tmp.add(toAccountDTO(i));
		}
		return tmp;
	}
}
