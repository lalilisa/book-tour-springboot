package com.ttcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttcs.model.dto.AccountDTO;
import com.ttcs.model.mapper.AccountMapper;
import com.ttcs.repository.AccountRepository;
import com.ttcs.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<AccountDTO> getUsers(){
		return AccountMapper.toListAccountDTOs(accountRepository.findByRole(1));
	}
	
	@Override
	public List<AccountDTO> getAdmins(){
		return AccountMapper.toListAccountDTOs(accountRepository.findByRole(0));
	}
	
	@Override
	public List<AccountDTO> getUsersByKey(String key){
		return AccountMapper.toListAccountDTOs(accountRepository.findByKey(key));
	}
	
	@Override
	public AccountDTO getAccByUsername(String username) {
		return AccountMapper.toAccountDTO(accountRepository.findByUserName(username));
	}
}
