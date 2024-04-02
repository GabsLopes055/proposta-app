package com.gabriel.propostaapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.NumberFormat;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class PropostaAppApplication {

	@Autowired
	private Locale locale;

	public static void main(String[] args) {
		SpringApplication.run(PropostaAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			double valor = Double.parseDouble("10.50");
//			System.out.println(NumberFormat.getCurrencyInstance(locale).format(valor));
//		};
//	}

}
