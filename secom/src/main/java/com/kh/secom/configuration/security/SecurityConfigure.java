package com.kh.secom.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigure {

	@Bean // @Bean을 통해 등록하는 경우 동일한 이름의 메소드 존재하면 안됨(오버로드 포함)
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//return httpSecurity.formLogin().disable().build(); => 이제 사용하지 않음
		/*
		return httpSecurity.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				t.disable();
			}
		}).httpBasic(null).csrf(null).cors(null).build(); // 신 문법
		*/ // 근본적인 방법 필요하지 않은 시큐리티를 모두 찾아 disable() => 모두 같은 부모클래스를 상속받고 있고 상속 받은 모든 객체들이 같은 메소드를 사용하고 있음 => 다형성 사용 가능, 람다 식 가능
		
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)// form 로그인 방식 사용하지 않겠다. 
				.httpBasic(AbstractHttpConfigurer::disable)  // httpBasic 사용 안함
				.csrf(AbstractHttpConfigurer::disable)  // csrf 사용 안함
				.cors(AbstractHttpConfigurer::disable) // react 작업 시 재설정 nginx 붙일 예정
				.build();
		/*
		 *  이러면 이제 컨트롤러에 도착.
		 *  body로 값을 보냈기 때문에 어디에 붙여서 값을 보냈는지 설정해줘야함 -> 컨트롤러에서
		 */
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
