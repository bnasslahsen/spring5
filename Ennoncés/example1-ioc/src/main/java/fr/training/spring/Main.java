package fr.training.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Developer john = ctx.getBean(Developer.class);
		john.doTask();

		DependencyInjectionDeveloper anna = new DependencyInjectionDeveloper(new ProgrammingTask());
		anna.doTask();

		DependencyInjectionDeveloper sam = (DependencyInjectionDeveloper) ctx.getBean("springDeveloper");
		sam.doTask();
	}
}
