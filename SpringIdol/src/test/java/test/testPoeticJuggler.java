package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.participants.PoeticJuggler;

public class testPoeticJuggler {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:bean.xml");
		PoeticJuggler poeticJuggler = (PoeticJuggler) ctx.getBean("poeticDuke");
		poeticJuggler.perform();
	}

}
