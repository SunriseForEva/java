package testspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");
		HelloWord obj = (HelloWord) context.getBean("helloWorld2");
		obj.getMessage();
		System.out.println(obj.getCount());
		
		System.out.println("\n");
		
		obj = (HelloWord) context.getBean("helloWorld");
		obj.getMessage();
		System.out.println(obj.getCount());
	}
}
