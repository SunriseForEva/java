package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import interfaces.Performer;

public class TestOneManBand {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		Performer instrumentalist = (Performer) ctx.getBean("hank");
		instrumentalist.perform();
	}

}
