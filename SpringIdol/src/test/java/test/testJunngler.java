package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import interfaces.Performer;

public class testJunngler {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");

		Performer performer = (Performer) ctx.getBean("duke");
		performer.perform();

		Performer performer2 = (Performer) ctx.getBean("duke");
		performer2.perform();

		System.out.println(performer + "  " + performer2);
	}
}
