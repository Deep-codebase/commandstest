package com.resources.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebSecurity
public class ManagementSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public ManagementSecurity(BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/management/list-customers").hasAnyRole("ADMIN","USER")//hasAnyRole("ADMIN")
		.antMatchers("/management/showVendor").hasRole("ADMIN")
		.anyRequest()
		.authenticated().and().csrf().disable()
		.formLogin().loginPage("/login").failureUrl("/login?error=true")
		.defaultSuccessUrl("/management/list-customers", true)
		.usernameParameter("email").passwordParameter("password")
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		.logout();
	} 

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		  auth
//	         .inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder)
//	         .withUser("deep").password("$2a$10$rYu5QcPxy.snclCdMfbp8OQBe7.kG8FfClGVnSd6eOLpu3.qAFahO").roles("USER");

		auth.authenticationProvider(customAuthenticationProvider);

	}
}
