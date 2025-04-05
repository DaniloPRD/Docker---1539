package br.edu.umfg.teste.spring;


import br.edu.umfg.teste.spring.entities.Fornecedor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = { "br.edu.umfg.teste.spring.entities" })
@EnableJpaRepositories(basePackages = { "br.edu.umfg.teste.spring.repositories" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
