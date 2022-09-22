package com.ttcs.controller.site;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttcs.domain.Account;
import com.ttcs.model.dto.AccountDTO;
import com.ttcs.repository.AccountRepository;
import com.ttcs.security.jwt.JwtFilter;
import com.ttcs.service.AccountService;

@Tag(name = "Account Controller")
@RestController
@RequestMapping("/data/secure")
public class AccountController {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@GetMapping(value="/getusers", produces = "application/json")
	public List<AccountDTO> getUsers(){
		return accountService.getUsers();
	}
	
	@GetMapping(value="/statisticusers", produces = "application/json")
	public Integer statisticUsers(){
		return accountService.getUsers().size();
	}
	
	@GetMapping(value="/getadmins", produces = "application/json")
	public List<AccountDTO> getAdmins(){
		return accountService.getAdmins();
	}
	
	@GetMapping(value="/getusersbykey", produces = "application/json")
	public List<AccountDTO> getUsersByKey(@RequestParam("key") String key){
		return accountService.getUsersByKey(key);
	}
	
	@GetMapping(value="/getaccbyusername", produces = "application/json")
	public AccountDTO getAccByUsername(@RequestParam("username") String username, @RequestHeader("Authorization") String token){
		if (jwtFilter.checkUsernameFromToken(username, token))
			return accountService.getAccByUsername(username);
		return new AccountDTO();
	}
	
	@PutMapping(value="/changepass", produces = "application/json")
	public String changePassword(@RequestBody AccountDTO acc, @RequestParam("pass") String pass) {
		Account a = accountRepository.findByUserName(acc.getUserName());
		if(!bCryptPasswordEncoder.matches(pass, a.getPassword())) {
			return "Mật khẩu cũ không đúng!";
		}
		a.setPassword(bCryptPasswordEncoder.encode(acc.getPassword()));
		accountRepository.save(a);
		return "Đổi thành công!";
	}
	
	@PutMapping(value="/changeimg", produces = "application/json")
	public String changePassword(@RequestBody AccountDTO acc) {
		Account a = accountRepository.findByUserName(acc.getUserName());
		a.setAvata(acc.getAvata());
		accountRepository.save(a);
		return "Đổi thành công!";
	}
	
	@PutMapping(value="/changeinfo", produces = "application/json")
	public String changeInfo(@RequestBody AccountDTO acc) {
		Account a = accountRepository.findByUserName(acc.getUserName());
		a.setName(acc.getName());
		a.setEmail(acc.getEmail());
		accountRepository.save(a);
		return "Đổi thành công!";
	}
	
	@DeleteMapping(value = "/deleteacc/{id}", produces = "application/json")
	public String deleteAcc(@PathVariable("id") Integer id) {
		accountRepository.deleteById(id);
		return "Xóa thành công!";
	}
}
