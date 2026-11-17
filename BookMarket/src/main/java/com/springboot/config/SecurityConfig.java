package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

   @Bean
	protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

    @Bean
    protected UserDetailsService users() {		
		UserDetails admin = User.builder()
			.username("Admin")
			.password(passwordEncoder().encode("Admin1234"))
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(admin);
   }
	
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
    	
    	http
    	.csrf(AbstractHttpConfigurer::disable)

	// 특정 URL에 대한 권한 설정.
        .authorizeHttpRequests(
        		authorizeRequests -> authorizeRequests            
        		.requestMatchers("/books/add").hasRole("ADMIN" )      
        		.anyRequest().permitAll()
        )
        .formLogin(
        		formLogin->formLogin
        		.loginPage("/login")//사용자 정의 로그인 페이지
        		.loginProcessingUrl("/login")//로그인 정의
        		.defaultSuccessUrl("/books/add")//로그인 성공시 이동하는 페이지
        		.failureUrl("/loginfailed")//로그인 실패시 실행
        		.usernameParameter("username")//사용자 이름(계정)
        		.passwordParameter("password")//사용자 계정 비밀번호
        		//Customizer.withDefaults()
        		)  
    	.logout(
    			logout->logout
    			.logoutUrl("/logout")
    			.logoutSuccessUrl("/login")
    			);
                
        return http.build();
        
    }

}
