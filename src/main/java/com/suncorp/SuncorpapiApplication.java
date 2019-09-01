package com.suncorp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SuncorpapiApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(SuncorpapiApplication.class);

	@Bean
	public Docket swagger(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.suncorp"))
				.build();
	}

	public static void main(String[] args) {
		LOGGER.debug("Inside Spring Boot Application");
		SpringApplication.run(SuncorpapiApplication.class, args);	
	}

}
