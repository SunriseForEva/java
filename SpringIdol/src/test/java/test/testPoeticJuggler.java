package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.PoeticJuggler;

public class testPoeticJuggler {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		PoeticJuggler juggler = (PoeticJuggler) ctx.getBean("poeticDuke");
		juggler.perform();
	}

}
