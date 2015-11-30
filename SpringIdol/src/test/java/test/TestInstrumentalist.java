package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.participants.Instrumentalist;

public class TestInstrumentalist {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		Instrumentalist instrumentalist = (Instrumentalist) ctx.getBean("kenny");
		instrumentalist.perform();

		Instrumentalist instrumentalist1 = (Instrumentalist) ctx.getBean("jenny");
		instrumentalist1.perform();

		Instrumentalist instrumentalist2 = (Instrumentalist) ctx.getBean("fenny");
		instrumentalist2.perform();
	}
}
