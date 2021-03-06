package fr.training.samples.spring.shop;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" }, lazyInit = true)
@EntityScan(basePackages = { "fr.training.samples.spring.shop.domain" })
@EnableJpaRepositories
public class SpringBootAppTest {}
