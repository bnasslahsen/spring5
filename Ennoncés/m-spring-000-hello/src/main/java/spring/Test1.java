package spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.bean.User;

public class Test1 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config-1.xml");
		User toto = (User) context.getBean("toto");
		String str = toto.toString();
		System.out.println("The result is " + str);
		context.close();
	}
}
