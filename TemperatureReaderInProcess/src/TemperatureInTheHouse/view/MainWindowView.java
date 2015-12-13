package TemperatureInTheHouse.view;

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
	private AreaChart<Number, Number> temperatureOutside = new AreaChart<Number, Number>(xAxisOut, yAxisOut);

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

	public XYChart.Series<Number, Number> getBalcony2() {
		return balcony2;
	}

	public void setBalcony2(XYChart.Series<Number, Number> balcony2) {
		this.balcony2 = balcony2;
	}

	public XYChart.Series<Number, Number> getBalcony1() {
		return balcony1;
	}

	public void setBalcony1(XYChart.Series<Number, Number> balcony1) {
		this.balcony1 = balcony1;
	}

	public XYChart.Series<Number, Number> getKitchen() {
		return kitchen;
	}

	public void setKitchen(XYChart.Series<Number, Number> kitchen) {
		this.kitchen = kitchen;
	}

	public XYChart.Series<Number, Number> getChildroom() {
		return childroom;
	}

	public void setChildroom(XYChart.Series<Number, Number> childroom) {
		this.childroom = childroom;
	}

	public XYChart.Series<Number, Number> getHall() {
		return hall;
	}

	public void setHall(XYChart.Series<Number, Number> hall) {
		this.hall = hall;
	}

	public XYChart.Series<Number, Number> getBadroom() {
		return badroom;
	}

	public void setBadroom(XYChart.Series<Number, Number> badroom) {
		this.badroom = badroom;
	}

	public XYChart.Series<Number, Number> getHallway() {
		return hallway;
	}

	public void setHallway(XYChart.Series<Number, Number> hallway) {
		this.hallway = hallway;
	}

	public XYChart.Series<Number, Number> getPantry() {
		return pantry;
	}

	public void setPantry(XYChart.Series<Number, Number> pantry) {
		this.pantry = pantry;
	}

	public XYChart.Series<Number, Number> getForest() {
		return forest;
	}

	public void setForest(XYChart.Series<Number, Number> forest) {
		this.forest = forest;
	}

	public XYChart.Series<Number, Number> getOuterYard() {
		return outerYard;
	}

	public void setOuterYard(XYChart.Series<Number, Number> outerYard) {
		this.outerYard = outerYard;
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

	public AreaChart<Number, Number> getTemperatureInside() {
		return temperatureInside;
	}

	public void setTemperatureInside(AreaChart<Number, Number> temperatureInside) {
		this.temperatureInside = temperatureInside;
	}

	public AreaChart<Number, Number> getTemperatureOutside() {
		return temperatureOutside;
	}

	public void setTemperatureOutside(AreaChart<Number, Number> temperatureOutside) {
		this.temperatureOutside = temperatureOutside;
	}

}
