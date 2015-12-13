package TemperatureInTheHouse.controller;

import TemperatureInTheHouse.view.MainWindowView;
import javafx.event.Event;
import javafx.event.EventHandler;

public class TestEvent implements EventHandler {
	private MainWindowView view;

	public TestEvent(MainWindowView view) {
		super();
		this.view = view;
	}

	public MainWindowView getView() {
		return view;
	}

	public void setView(MainWindowView view) {
		this.view = view;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void handle(Event event) {
		/*System.gc();
		view.setDisplayingOneDay(true);
		view.setDisplayingOneHour(false);
		setScale(MainWindowView.getONE_DAY(), view.getxAxisOut(), view.getyAxisOut());
		setScale(ONE_DAY, xAxisOut, yAxisOut);
		showDisplayTemperatureOutside(main.getDay(), ONE_DAY);
		labelPeriodDaySecondTab.setText("" + main.getDisplayingDay().getDate() + " . "
				+ ((main.getDisplayingDay().getMonth()) + 1) + " . " + (main.getDisplayingDay().getYear() + 1900));
		labelPeriodHourSecondTab.setText("from 00:00 to 24:00 ");
		System.gc();*/
	}
}