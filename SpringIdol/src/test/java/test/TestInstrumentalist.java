package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.Instrumentalist;
import classes.PoeticJuggler;

public class TestInstrumentalist {
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		Instrumentalist instrumentalist = (Instrumentalist) ctx.getBean("kenny");
		System.out.println("I'm " + instrumentalist.getAge());
		System.out.println("This is my song:");
		System.out.println(instrumentalist.getSong());
		instrumentalist.perform();
	}
}
