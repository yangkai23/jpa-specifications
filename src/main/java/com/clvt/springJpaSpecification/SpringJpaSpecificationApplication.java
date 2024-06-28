package com.clvt.springJpaSpecification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.clvt.springJpaSpecification"})
public class SpringJpaSpecificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSpecificationApplication.class, args);
	}

}
