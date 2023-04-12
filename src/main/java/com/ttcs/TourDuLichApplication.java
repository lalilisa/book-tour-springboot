package com.ttcs;

import com.ttcs.domain.Account;
import com.ttcs.repository.AccountRepository;
import com.ttcs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TourDuLichApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourDuLichApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	CommandLineRunner init(AccountRepository accountRepository) {
		return (args) -> {
				Account a=accountRepository.findByUserName("trimai");
				if(a==null){
				Account admin=new Account();
				admin.setUserName("trimai");
				admin.setEmail("maivantri309@gmail.com");
				admin.setPassword((bCryptPasswordEncoder.encode("123")));
				accountRepository.save(admin);
			}
		};
	}
}
