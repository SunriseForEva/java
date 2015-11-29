package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.Instrumentalist;

public class TestInstrumentalist {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		Instrumentalist instrumentalist = (Instrumentalist) ctx.getBean("kenny");
		instrumentalist.perform();
		System.out.println(instrumentalist.getInstrument());

		Instrumentalist instrumentalist1 = (Instrumentalist) ctx.getBean("jenny");
		instrumentalist1.perform();
		System.out.println(instrumentalist1.getInstrument());
	}
}
