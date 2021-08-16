package com.mba.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@SpringBootApplication
@EnableJpaAuditing
public class BankTransactionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankTransactionsApplication.class, args);
  }

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
    b.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    return b;
  }
}
