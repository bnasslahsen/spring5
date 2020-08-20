package fr.training.samples.spring.shop;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootConfiguration
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" }, lazyInit = true)
public class SpringBootAppTest {

}
