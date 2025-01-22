package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	/*
	 *  Java 기반 설정을 통해 사용해야하는 Bean 정의
	 *  
	 *  @Configuration
	 *  스프링의 설정클래스를 정의할 때 사용
	 *  
	 *  하나 이상의 @Bean이 달린 메소드를 포함해 스프링 컨테이너에 빈을 등록함
	 *  
	 *  @Bean
	 *  @Configuration 클래스 내에서 메소드에 적용되어 스프링 빈을 생성하고 관리
	 *  
	 *  메소드 반환값이 스프링 컨테이너에 의해 빈으로 등록됨.
	 *  
	 *  => XML 설정보다 빠른 시점에 오류를 발견하고 코드기반이기 때문에 자동완성 및 수정이 용이하며 설정 클래스 내에서 빈의 생성과정을 명확하게 정의할 수 있음.
	 *  
	 *  
	 *  이전 방식
	 *  
	 *  root-context.xml
	 *  <bean class="">
	 *  	<property 필드 세팅>
	 *  </bean>
	 * 
	 */
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
	
}
