package classes.participants;

import interfaces.Performer;

public class Juggler implements Performer {
	private int beanBags = 3;

	public Juggler() {

	}

	public Juggler(int beanBags) {
		super();
		this.beanBags = beanBags;
	}

	@Override
	public void perform() {
		System.out.println("JUGGLING " + beanBags + " BEANBAGS");
	}

}
