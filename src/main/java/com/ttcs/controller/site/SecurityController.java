package com.ttcs.controller.site;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttcs.domain.Account;
import com.ttcs.model.dto.AccountDTO;
import com.ttcs.repository.AccountRepository;
import com.ttcs.security.LoginRequest;
import com.ttcs.security.jwt.JwtResponse;
import com.ttcs.security.jwt.JwtUtility;
import com.ttcs.security.service.AccountDetailsImpl;

import net.bytebuddy.utility.RandomString;

@RestController
public class SecurityController {
	@Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles)
        );
    }
    
    @GetMapping(value="/authenticate", produces = "application/json")
    public boolean authenticate(@RequestHeader("Authorization") String token) {
    	return jwtUtility.validateJwtToken(token);
    }

    @PostMapping(value="/signup", produces = "application/json")
    public String registerUser(@RequestBody AccountDTO accountDTO){

    	if(accountRepository.findByUserName(accountDTO.getUserName())!=null) {
    		return "Fail";
    	}
        Account account = new Account();
        account.setUserName(accountDTO.getUserName());
        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setRole(accountDTO.getRole());
        account.setAvata(accountDTO.getAvata());
        accountRepository.save(account);
        return "Success";
    }
    
    @GetMapping(value="/forgot", produces = "application/json")
    public String forgotPass(@RequestParam("username") String username, @RequestParam("email") String email) {
    	Account acc = accountRepository.findByUserName(username);
    	if (acc==null || !acc.getEmail().equals(email)) {
    		return "Fail";
    	}
    	SimpleMailMessage mail = new SimpleMailMessage();
    	String pass = RandomString.make(10);
    	acc.setPassword(bCryptPasswordEncoder.encode(pass));
    	accountRepository.save(acc);
    	mail.setTo(email);
    	mail.setSubject("Khôi phục mật khẩu");
    	mail.setText("Mật khẩu mới của bạn là: "+pass);
    	javaMailSender.send(mail);
    	return "Success";
    }
}
