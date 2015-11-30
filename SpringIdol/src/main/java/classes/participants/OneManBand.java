package classes.participants;

import java.util.Collection;

import interfaces.Instrument;
import interfaces.Performer;

public class OneManBand implements Performer {
	private Collection<Instrument> instruments;

	@Override
	public void perform() {
		for (Instrument instrument : instruments)
			instrument.play();
	}

	public Collection<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Collection<Instrument> instruments) {
		this.instruments = instruments;
	}

}
