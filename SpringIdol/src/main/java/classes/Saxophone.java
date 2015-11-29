package classes;

import interfaces.Instrument;

public class Saxophone implements Instrument {

	@Override
	public void play() {
		System.out.println("TOOT TOOT TOOT");
	}

	public Saxophone() {
		super();
	}
}
