package com.endereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.endereco")
public class EnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnderecoApplication.class, args);
	}

}
