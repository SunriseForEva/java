package TemperatureInTheHouse.view;

import TemperatureInTheHouse.Main;
import TemperatureInTheHouse.model.TemperatureInTheHouse;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainWindowView {
	private TemperatureInTheHouse temperature;
	private Main main;
	private static int ONE_HOUR = 60;
	private static int ONE_DAY = 24;
	boolean displayingOneHour;
	boolean displayingOneDay;
	boolean pause;

	XYChart.Series<Number, Number> balcony2;
	XYChart.Series<Number, Number> balcony1;
	XYChart.Series<Number, Number> kitchen;
	XYChart.Series<Number, Number> childroom;
	XYChart.Series<Number, Number> hall;
	XYChart.Series<Number, Number> badroom;
	XYChart.Series<Number, Number> hallway;
	XYChart.Series<Number, Number> pantry;
	XYChart.Series<Number, Number> forest;
	XYChart.Series<Number, Number> outerYard;

	@FXML
	ImageView picture;
	@FXML
	private Label textFlow1;
	@FXML
	private Label textFlow2;
	@FXML
	private Label textFlow3;
	@FXML
	private Label textFlow4;
	@FXML
	private Label textFlow5;
	@FXML
	private Label textFlow6;
	@FXML
	private Label textFlow7;
	@FXML
	private Label textFlow8;
	@FXML
	private Label textFlow9;
	@FXML
	private Label labelPeriodDaySecondTab;
	@FXML
	private Label labelPeriodHourSecondTab;
	@FXML
	private Label labelPeriodDayThirdTab;
	@FXML
	private Label labelPeriodHourThirdTab;

	@FXML
	final NumberAxis xAxisOut = new NumberAxis(0, 0, 0);
	@FXML
	final NumberAxis yAxisOut = new NumberAxis(0, 0, 0);
	@FXML
	final NumberAxis xAxisIn = new NumberAxis(0, 0, 0);
	@FXML
	final NumberAxis yAxisIn = new NumberAxis(0, 0, 0);

	@FXML
	private AreaChart<Number, Number> temperatureInside = new AreaChart<Number, Number>(xAxisIn, yAxisIn);
	@FXML
	private AreaChart<Number, Number> temperatureOutside = new AreaChart<Number, Number>(xAxisOut, yAxisOut);;

	@FXML
	Tab secondTab;
	@FXML
	Tab thirdTab;
	@FXML
	Tab firstTab;

	@FXML
	Pane paneForInsideTeperature;
	@FXML
	Pane paneForOutsideTeperature;

	@FXML
	Button oneHourThirdTab;
	@FXML
	Button oneDayThirdTab;
	@FXML
	Button backwardThirdTab;
	@FXML
	Button pauseThirdTab;
	@FXML
	Button forwardThirdTab;

	@FXML
	Button oneHourSecondTab;
	@FXML
	Button oneDaySecondTab;
	@FXML
	Button backwardSecondTab;
	@FXML
	Button pauseSecondTab;
	@FXML
	Button forwardSecondTab;

	@FXML
	CheckBox temperatureInTheBalcony1 = new CheckBox();
	@FXML
	CheckBox temperatureInTheBalcony2 = new CheckBox();
	@FXML
	CheckBox temperatureInTheHall = new CheckBox();
	@FXML
	CheckBox temperatureInTheBadRoom = new CheckBox();
	@FXML
	CheckBox temperatureInTheHallWay = new CheckBox();
	@FXML
	CheckBox temperatureInTheKitchen = new CheckBox();
	@FXML
	CheckBox temperatureInThePantry = new CheckBox();
	@FXML
	CheckBox temperatureInTheChildRoom = new CheckBox();
	@FXML
	CheckBox temperatureInTheForest = new CheckBox();
	@FXML
	CheckBox temperatureInTheOuterYard = new CheckBox();

	public TemperatureInTheHouse getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureInTheHouse temperature) {
		this.temperature = temperature;
	}

	public static int getONE_HOUR() {
		return ONE_HOUR;
	}

	public static void setONE_HOUR(int oNE_HOUR) {
		ONE_HOUR = oNE_HOUR;
	}

	public static int getONE_DAY() {
		return ONE_DAY;
	}

	public static void setONE_DAY(int oNE_DAY) {
		ONE_DAY = oNE_DAY;
	}

	public boolean isDisplayingOneHour() {
		return displayingOneHour;
	}

	public void setDisplayingOneHour(boolean displayingOneHour) {
		this.displayingOneHour = displayingOneHour;
	}

	public boolean isDisplayingOneDay() {
		return displayingOneDay;
	}

	public void setDisplayingOneDay(boolean displayingOneDay) {
		this.displayingOneDay = displayingOneDay;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public NumberAxis getxAxisOut() {
		return xAxisOut;
	}

	public NumberAxis getyAxisOut() {
		return yAxisOut;
	}

	public NumberAxis getxAxisIn() {
		return xAxisIn;
	}

	public NumberAxis getyAxisIn() {
		return yAxisIn;
	}

}
