package com.ttcs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ttcs.security.jwt.JwtFilter;
import com.ttcs.security.service.AccountDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountDetailServiceImpl accountService;
    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**", "/views/**", "/index", "/tour_list", "/tour_detail",
                			"/forgotPass", "/login", "/register","/","/cookie.js","/userInfo","/userBooked").permitAll()
                .antMatchers(HttpMethod.GET,"/authenticate", "/data/category","/data/categoryid",
                		"/data/hotsale","/data/region","/data/fulllist","/data/searchtour",
                		"/data/pagetour","/data/tour/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/data/secure/getusers", "/data/secure/getadmins",
                		"/data/secure/getusersbykey","/data/secure/listbooktourbyaccid",
                		"/data/secure/booktour/{id}","/data/secure/statisticusers","/data/statistictour", 
                		"/data/secure/statisticincome", "/data/secure/statisticbooktour").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/data/secure/listbooktourbyusername").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers(HttpMethod.POST,"/signin", "/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/data/tour").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/data/tour/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/data/secure/changepass","/data/secure/changeimg",
                		"/data/secure/changeinfo").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers(HttpMethod.DELETE,"/data/secure/deleteacc/{id}","/data/secure/booktour/{id}",
                		"/data/tour/{id}").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
