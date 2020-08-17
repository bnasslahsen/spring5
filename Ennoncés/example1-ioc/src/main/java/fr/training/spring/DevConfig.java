package fr.training.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfig {
	@Bean
	DependencyInjectionDeveloper springDeveloper(DocumentingTask documentingTask) {
		return new DependencyInjectionDeveloper(documentingTask);
	}
}
