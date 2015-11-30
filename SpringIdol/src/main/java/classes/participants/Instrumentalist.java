package classes.participants;

import interfaces.Instrument;
import interfaces.Performer;

public class Instrumentalist implements Performer {
	private String song;
	private Instrument instrument;
	int age;

	public Instrumentalist() {
		super();
	}

	@Override
	public void perform() {
		System.out.print("Playing " + song + " : ");
		instrument.play();
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
