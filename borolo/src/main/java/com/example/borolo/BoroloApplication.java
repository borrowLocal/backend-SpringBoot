package com.example.borolo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@MapperScan("com.example.borolo.repository")
@OpenAPIDefinition( //Swagger 설정
	    info = @Info(
	        title = "Borolo API",
	        version = "1.0",
	        description = "Borolo 프로젝트 API 상세 설명"
	    )
	)
public class BoroloApplication {

	public static void main(String[] args) {	
		SpringApplication.run(BoroloApplication.class, args);
	}

}
