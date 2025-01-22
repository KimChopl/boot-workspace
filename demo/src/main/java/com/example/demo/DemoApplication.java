package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	
	/*
	 *  Starter POMs
	 *  
	 *  특정 기능에 필요한 의존성을 한 번에 관리할 수 있는 개념
	 *  
	 *  각각의 Starter는 관련된 라이브러리들의 집합으로 모든 의존성을 하나의 Starter로 쉽게 추가할 수 있음.
	 *  
	 *  장점 : 필요한 기능에 맞는 Starter만 추가하여 의존성을 직접 관리할 필요가 없어짐
	 *  	  모든 개발자가 동일한 Starter를 사용하여 프로젝트 간 의존성 충돌 방지가 가능함
	 *  
	 *  Starter에 모든 라이브러리가 존재하지는 않음.	
	 */
	
	/*
	 *  AutoConfiguration(자동 구성)
	 *  
	 *  스프링부트의 핵심 기능
	 *  어플리케이션의 클래스페스에 존재하는 라이브러리와 설정을 기반으로 Bean 자동 구성
	 *  
	 *  EvableAutoConfiguration
	 *  스프링부트의 자동구성을 활성화 해주는 애노테이션
	 *  SpringBootApplication 내부에 포함되어 있어 직접 작성할 경우는 많이 없음
	 *  
	 *  SpringBootApplication
	 *  스프링부트 애플리케이션의 진입점 클래스에 사용되는 애노테이션
	 *  핵심 애노테이션
	 *  - EnableAutoConfiguration
	 *  - ComponentScan
	 *  - Configuration
	 *  
	 *  동작 순서
	 *  
	 *  애플리케이션 시작 -> @SpringBootApplication이 있는 클래스의 main메소드에서 run 호출
	 *  자동구성 활성화 -> @EnableAutoConfiguration을 통해 자동구성 활성화
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
