package com.sutisoft.contentmanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.authorizeRequests().antMatchers("/","/index","/css/*","/js/*")
				.permitAll().antMatchers("/products/**")
				.hasAnyRole(ApplicationUserRole.STUDENT.name(),ApplicationUserRole.ADMIN.name())
				.anyRequest()
				.authenticated()
				.and()
				//.httpBasic();
				.formLogin().loginPage("/login").permitAll();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager=null;
		
		UserDetails testUser= User
												.builder()
												.username("test")
												.password(passwordEncoder.encode("test"))
												.roles(ApplicationUserRole.STUDENT.name())
												.build();
		
		UserDetails adminUser= User
													.builder()
													.username("linda")
													.password(passwordEncoder.encode("password123"))
													.roles(ApplicationUserRole.ADMIN.name())
													.build();
		
		inMemoryUserDetailsManager=new InMemoryUserDetailsManager(testUser,adminUser);
		return inMemoryUserDetailsManager;
	}

	
}
