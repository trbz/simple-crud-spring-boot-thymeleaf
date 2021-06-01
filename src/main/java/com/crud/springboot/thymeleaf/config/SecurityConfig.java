package com.crud.springboot.thymeleaf.config;

import javax.sql.DataSource;

import com.crud.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	@Qualifier("securityDataSource")
//	private DataSource securityDataSource;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.jdbcAuthentication().dataSource(securityDataSource);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/employees/addForm").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/employees/updateForm").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/employees/save").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/employees/delete").hasRole("ADMIN")
			.antMatchers("/employees/**").hasRole("EMPLOYEE")
			.antMatchers("/users/**").hasRole("ADMIN")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
		
}






