package com.ttcs.service;

import java.util.List;

import com.ttcs.model.dto.AccountDTO;

public interface AccountService {

	List<AccountDTO> getAdmins();

	List<AccountDTO> getUsers();

	List<AccountDTO> getUsersByKey(String key);

	AccountDTO getAccByUsername(String username);

}
