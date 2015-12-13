package TemperatureInTheHouse.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import util.CreateConnectionDB;
import TemperatureInTheHouse.Main;
import TemperatureInTheHouse.model.TemperatureInTheHouse;

public class MainWindowController {
	private TemperatureInTheHouse model;
	private CreateConnectionDB db;

	private Main main;
	private static int ONE_HOUR = 60;
	private static int ONE_DAY = 24;
	boolean displayingOneHour;
	boolean displayingOneDay;
	boolean pause;

	private TemperatureInTheHouse currentValueOfTheData;
	static int count = 0;
	private ArrayList<TemperatureInTheHouse> day = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> hour = new ArrayList<TemperatureInTheHouse>();

	int displayingHour;
	Date displayingDay;

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
	private AreaChart<Number, Number> temperatureInside = new AreaChart<Number, Number>(
			xAxisIn, yAxisIn);
	@FXML
	private AreaChart<Number, Number> temperatureOutside = new AreaChart<Number, Number>(
			xAxisOut, yAxisOut);;

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

	public MainWindowController() {
		System.out.println("Constructor");
		try {
			currentValueOfTheData = new TemperatureInTheHouse();
			db = CreateConnectionDB.getInstance();
			db.setLastRecord();
			displayingHour = db.getCurrentDay().getHours();
			displayingDay = db.getCurrentDay();
			db.setCurrentDayData();
			db.setCurrentHourData(displayingDay);
			currentValueOfTheData = db.getLastRecord();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setCurrentDate();
		setCurrentHour();
		getTemperatureLastDay();
		getTemperatureLastHour();
	}

	@FXML
	public void initialize() {
		System.out.println("initialize");
		displayingOneHour = true;
		pause = false;

		labelPeriodDaySecondTab = new Label();
		labelPeriodDaySecondTab.setLayoutX(3);
		labelPeriodDaySecondTab.setAlignment(Pos.CENTER);
		labelPeriodDaySecondTab.setLayoutY(400);
		labelPeriodDaySecondTab.setMinSize(105, 20);
		labelPeriodDaySecondTab.setFont(new Font(11));

		labelPeriodHourSecondTab = new Label();
		labelPeriodHourSecondTab.setLayoutX(3);
		labelPeriodHourSecondTab.setAlignment(Pos.CENTER);
		labelPeriodHourSecondTab.setLayoutY(415);
		labelPeriodHourSecondTab.setMinSize(105, 20);
		labelPeriodHourSecondTab.setFont(new Font(11));

		labelPeriodDayThirdTab = new Label();
		labelPeriodDayThirdTab.setLayoutX(3);
		labelPeriodDayThirdTab.setAlignment(Pos.CENTER);
		labelPeriodDayThirdTab.setLayoutY(400);
		labelPeriodDayThirdTab.setMinSize(105, 20);
		labelPeriodDayThirdTab.setFont(new Font(11));

		labelPeriodHourThirdTab = new Label();
		labelPeriodHourThirdTab.setLayoutX(3);
		labelPeriodHourThirdTab.setAlignment(Pos.CENTER);
		labelPeriodHourThirdTab.setLayoutY(415);
		labelPeriodHourThirdTab.setMinSize(105, 20);
		labelPeriodHourThirdTab.setFont(new Font(11));

		temperatureInTheBalcony1.setSelected(true);
		temperatureInTheBalcony2.setSelected(true);
		temperatureInTheHall.setSelected(true);
		temperatureInTheBadRoom.setSelected(true);
		temperatureInTheHallWay.setSelected(true);
		temperatureInTheKitchen.setSelected(true);
		temperatureInThePantry.setSelected(true);
		temperatureInTheChildRoom.setSelected(true);
		temperatureInTheForest.setSelected(true);
		temperatureInTheOuterYard.setSelected(true);

		setScale(ONE_HOUR, xAxisOut, yAxisOut);
		temperatureOutside = new AreaChart<Number, Number>(xAxisOut, yAxisOut);

		setScale(ONE_HOUR, xAxisIn, yAxisIn);
		temperatureInside = new AreaChart<Number, Number>(xAxisIn, yAxisIn);

		oneDaySecondTab = new Button("24 hours");
		oneDaySecondTab.setLayoutX(13);
		oneDaySecondTab.setLayoutY(563);
		oneDaySecondTab.setMinSize(70, 25);
		oneDaySecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent arg0) {
				System.gc();
				displayingOneDay = true;
				displayingOneHour = false;
				setScale(ONE_DAY, xAxisOut, yAxisOut);
				showDisplayTemperatureOutside(getDay(), ONE_DAY);
				labelPeriodDaySecondTab.setText(""
						+ getDisplayingDay().getDate() + " . "
						+ ((getDisplayingDay().getMonth()) + 1) + " . "
						+ (getDisplayingDay().getYear() + 1900));
				labelPeriodHourSecondTab.setText("from 00:00 to 24:00 ");
				System.gc();
			}
		});

		oneHourSecondTab = new Button("1 hour");
		oneHourSecondTab.setLayoutX(13);
		oneHourSecondTab.setLayoutY(520);
		oneHourSecondTab.prefHeight(25);
		oneHourSecondTab.setMinSize(70, 25);

		oneHourSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent arg0) {
				System.gc();
				displayingOneDay = false;
				displayingOneHour = true;
				setScale(ONE_HOUR, xAxisOut, yAxisOut);
				showDisplayTemperatureOutside(getHour(), ONE_HOUR);
				labelPeriodDaySecondTab.setText(""
						+ (getDisplayingDay().getDate()) + " . "
						+ ((getDisplayingDay().getMonth()) + 1) + " . "
						+ (getDisplayingDay().getYear() + 1900));
				labelPeriodHourSecondTab.setText("from " + getDisplayHour()
						+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				System.gc();
			}
		});

		forwardSecondTab = new Button(">");
		forwardSecondTab.setLayoutX(48);
		forwardSecondTab.setLayoutY(475);
		forwardSecondTab.setMinSize(35, 26);
		forwardSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneHour == true) {
					getNextHour();
					showDisplayTemperatureOutside(getHour(), ONE_HOUR);
					labelPeriodDaySecondTab.setText(""
							+ (getDisplayingDay().getDate()) + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourSecondTab.setText("from " + getDisplayHour()
							+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				}
				if (displayingOneDay == true) {
					getNextDay();
					showDisplayTemperatureOutside(getDay(), ONE_DAY);
					labelPeriodDaySecondTab.setText(""
							+ getDisplayingDay().getDate() + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourSecondTab.setText("from 00:00 to 24:00 ");
				}
				System.gc();
			}
		});

		pauseSecondTab = new Button("refresh");
		pauseSecondTab.setLayoutX(13);
		pauseSecondTab.setLayoutY(445);
		pauseSecondTab.setMinSize(70, 25);
		pauseSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (displayingOneHour == true) {
					System.gc();
					main = main.refreshAllData();
					showDisplayTemperatureOutside(getHour(), ONE_HOUR);
					System.gc();
				}
				if (displayingOneDay == true) {
					System.gc();
					main = main.refreshAllData();
					showDisplayTemperatureOutside(getDay(), ONE_HOUR);
					System.gc();
				}
			}
		});

		backwardSecondTab = new Button("<");
		backwardSecondTab.setLayoutX(12);
		backwardSecondTab.setLayoutY(475);
		backwardSecondTab.setMinSize(35, 26);
		backwardSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneHour == true) {
					getPreviousHour();
					showDisplayTemperatureOutside(getHour(), ONE_HOUR);
					labelPeriodDaySecondTab.setText(""
							+ (getDisplayingDay().getDate()) + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourSecondTab.setText("from " + getDisplayHour()
							+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				}
				if (displayingOneDay == true) {
					getPreviousDay();
					showDisplayTemperatureOutside(getDay(), ONE_DAY);
					labelPeriodDaySecondTab.setText(""
							+ getDisplayingDay().getDate() + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourSecondTab.setText("from 00:00 to 24:00 ");
				}
				System.gc();
			}
		});

		temperatureInTheForest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureOutside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureOutside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheOuterYard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureOutside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureOutside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		oneDayThirdTab = new Button("24 hours");
		oneDayThirdTab.setLayoutX(13);
		oneDayThirdTab.setLayoutY(563);
		oneDayThirdTab.setMinSize(70, 25);
		oneDayThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent arg0) {
				System.gc();
				displayingOneDay = true;
				displayingOneHour = false;
				setScale(ONE_DAY, xAxisIn, yAxisIn);
				showDisplayTemperatureInside(getDay(), ONE_DAY);
				labelPeriodDayThirdTab.setText(""
						+ getDisplayingDay().getDate() + " . "
						+ ((getDisplayingDay().getMonth()) + 1) + " . "
						+ (getDisplayingDay().getYear() + 1900));
				labelPeriodHourThirdTab.setText("from 00:00 to 24:00 ");
				System.gc();
			}
		});

		oneHourThirdTab = new Button("1 hour");
		oneHourThirdTab.setLayoutX(13);
		oneHourThirdTab.setLayoutY(520);
		oneHourThirdTab.prefHeight(25);
		oneHourThirdTab.setMinSize(70, 25);
		oneHourThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent arg0) {
				System.gc();
				displayingOneDay = false;
				displayingOneHour = true;
				setScale(ONE_HOUR, xAxisIn, yAxisIn);
				showDisplayTemperatureInside(getHour(), ONE_HOUR);
				labelPeriodDayThirdTab.setText(""
						+ (getDisplayingDay().getDate()) + " . "
						+ ((getDisplayingDay().getMonth()) + 1) + " . "
						+ (getDisplayingDay().getYear() + 1900));
				labelPeriodHourThirdTab.setText("from " + getDisplayHour()
						+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				System.gc();
			}
		});

		forwardThirdTab = new Button(">");
		forwardThirdTab.setLayoutX(48);
		forwardThirdTab.setLayoutY(475);
		forwardThirdTab.setMinSize(35, 26);
		forwardThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneHour == true) {
					getNextHour();
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
					labelPeriodDayThirdTab.setText(""
							+ (getDisplayingDay().getDate()) + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourThirdTab.setText("from " + getDisplayHour()
							+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				}
				if (displayingOneDay == true) {
					getNextDay();
					showDisplayTemperatureInside(getDay(), ONE_DAY);
					labelPeriodDayThirdTab.setText(""
							+ getDisplayingDay().getDate() + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourThirdTab.setText("from 00:00 to 24:00 ");
				}
				System.gc();
			}
		});
		// ------------------------------------------------------------------------------
		pauseThirdTab = new Button("refresh");
		pauseThirdTab.setLayoutX(13);
		pauseThirdTab.setLayoutY(445);
		pauseThirdTab.setMinSize(70, 25);
		pauseThirdTab.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (displayingOneHour == true) {
					System.gc();
					main = main.refreshAllData();
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
					System.gc();
				}
				if (displayingOneDay == true) {
					System.gc();
					main = main.refreshAllData();
					showDisplayTemperatureInside(getDay(), ONE_HOUR);
					System.gc();
				}
			}
		});
		// ------------------------------------------------------------------------------
		backwardThirdTab = new Button("<");
		backwardThirdTab.setLayoutX(12);
		backwardThirdTab.setLayoutY(475);
		backwardThirdTab.setMinSize(35, 26);
		backwardThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneHour == true) {
					getPreviousHour();
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
					labelPeriodDayThirdTab.setText(""
							+ (getDisplayingDay().getDate()) + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourThirdTab.setText("from " + getDisplayHour()
							+ " : 00 to " + (getDisplayHour() + 1) + " : 00");
				}
				if (displayingOneDay == true) {
					getPreviousDay();
					showDisplayTemperatureInside(getDay(), ONE_DAY);
					labelPeriodDayThirdTab.setText(""
							+ getDisplayingDay().getDate() + " . "
							+ ((getDisplayingDay().getMonth()) + 1) + " . "
							+ (getDisplayingDay().getYear() + 1900));
					labelPeriodHourThirdTab.setText("from 00:00 to 24:00 ");
				}
				System.gc();
			}
		});

		temperatureInTheBadRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheBalcony1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheBalcony2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheChildRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheHall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheHallWay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheKitchen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInTheBadRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

		temperatureInThePantry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if (displayingOneDay == true) {
					showDisplayTemperatureInside(getDay(), ONE_DAY);
				}
				if (displayingOneHour == true) {
					showDisplayTemperatureInside(getHour(), ONE_HOUR);
				}
				System.gc();
			}
		});

	}

	// public void setMainApp(Main main) {
	// this.main = main;
	// setTemperature(main.getCurrentValueOfTheData());
	//
	// showDisplayTemperatureInside(getHour(), ONE_HOUR);
	// showDisplayTemperatureOutside(getHour(), ONE_HOUR);
	//
	// // Thread update = new Thread(new UpdateGUI());
	// // update.setDaemon(true);
	// // update.start();
	// }

	public void setTemperature(TemperatureInTheHouse t) {
		model = t;
		textFlow1.setText(String.valueOf(model.getBalcony_1()));
		textFlow2.setText(String.valueOf(model.getBadRoom()));
		textFlow3.setText(String.valueOf(model.getHall()));
		textFlow4.setText(String.valueOf(model.getBalcony_2()));
		textFlow5.setText(String.valueOf(model.getChildRoom()));
		textFlow6.setText(String.valueOf(model.getHallWay()));
		textFlow7.setText(String.valueOf(model.getKitchen()));
		textFlow8.setText(String.valueOf(model.getPantry()));
		textFlow9.setText(String.valueOf(model.getCurrentDate()));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void showDisplayTemperatureInside(
			ArrayList<TemperatureInTheHouse> outsideArray, int scale) {
		activateForwardButtons();

		labelPeriodDaySecondTab.setText("" + (getDisplayingDay().getDate())
				+ " . " + ((getDisplayingDay().getMonth()) + 1) + " . "
				+ (getDisplayingDay().getYear() + 1900));
		labelPeriodHourSecondTab.setText("from " + getDisplayHour()
				+ " : 00 to " + (getDisplayHour() + 1) + " : 00");

		balcony2 = new XYChart.Series<Number, Number>();
		balcony1 = new XYChart.Series<Number, Number>();
		kitchen = new XYChart.Series<Number, Number>();
		childroom = new XYChart.Series<Number, Number>();
		hall = new XYChart.Series<Number, Number>();
		badroom = new XYChart.Series<Number, Number>();
		hallway = new XYChart.Series<Number, Number>();
		pantry = new XYChart.Series<Number, Number>();

		balcony1.setName("balcony1");
		balcony2.setName("balcony2");
		kitchen.setName("kitchen");
		childroom.setName("childrenroom");
		hall.setName("hall");
		badroom.setName("badroom");
		hallway.setName("hallway");
		pantry.setName("pantry");

		for (int i = 0; i < outsideArray.size(); i++) {
			if (temperatureInTheBalcony1.isSelected()) {
				balcony1.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getBalcony_1()));
			}
			if (temperatureInTheBalcony2.isSelected()) {
				balcony2.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getBalcony_2()));
			}
			if (temperatureInTheKitchen.isSelected()) {
				kitchen.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getKitchen()));
			}
			if (temperatureInTheChildRoom.isSelected()) {
				childroom.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getChildRoom()));
			}
			if (temperatureInTheHall.isSelected()) {
				hall.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getHall()));
			}
			if (temperatureInTheBadRoom.isSelected()) {
				badroom.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getBadRoom()));
			}
			if (temperatureInTheHallWay.isSelected()) {
				hallway.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getHallWay()));
			}
			if (temperatureInThePantry.isSelected()) {
				pantry.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getPantry()));
			}
		}

		temperatureInside.getData().clear();
		temperatureInside.getData().addAll(balcony1, balcony2, kitchen,
				childroom, hallway, hall, badroom, pantry);
		temperatureInside.setPrefHeight(640);
		temperatureInside.setPrefWidth(700);
		temperatureInside.setLayoutX(110);
		temperatureInside.setLayoutY(6);

		paneForInsideTeperature = new Pane(temperatureInside,
				temperatureInTheBalcony1, temperatureInTheBalcony2,
				temperatureInTheBadRoom, temperatureInTheHall,
				temperatureInTheChildRoom, temperatureInTheHallWay,
				temperatureInTheKitchen, temperatureInThePantry,
				oneDayThirdTab, oneHourThirdTab, forwardThirdTab,
				pauseThirdTab, backwardThirdTab, labelPeriodDayThirdTab,
				labelPeriodHourThirdTab);
		thirdTab.setContent(paneForInsideTeperature);
		System.gc();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void showDisplayTemperatureOutside(
			ArrayList<TemperatureInTheHouse> outsideArray, int scale) {
		activateForwardButtons();

		labelPeriodDayThirdTab.setText("" + (getDisplayingDay().getDate())
				+ " . " + ((getDisplayingDay().getMonth()) + 1) + " . "
				+ (getDisplayingDay().getYear() + 1900));
		labelPeriodHourThirdTab.setText("from " + getDisplayHour()
				+ " : 00 to " + (getDisplayHour() + 1) + " : 00");

		forest = new XYChart.Series<Number, Number>();
		outerYard = new XYChart.Series<Number, Number>();

		forest.setName("forest");
		outerYard.setName("outeryard");

		for (int i = 0; i < outsideArray.size(); i++) {
			if (temperatureInTheForest.isSelected()) {
				forest.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getOuterForest()));
			}
			if (temperatureInTheOuterYard.isSelected()) {
				outerYard.getData().add(
						new XYChart.Data<Number, Number>(getTime(outsideArray
								.get(i).getCurrentDate(), scale), outsideArray
								.get(i).getOuterYard()));
			}
		}

		temperatureOutside.getData().clear();
		temperatureOutside.getData().addAll(forest, outerYard);
		temperatureOutside.setPrefHeight(640);
		temperatureOutside.setPrefWidth(722);
		temperatureOutside.setLayoutX(80);
		temperatureOutside.setLayoutY(6);

		paneForInsideTeperature = new Pane(temperatureOutside,
				temperatureInTheForest, temperatureInTheOuterYard,
				oneDaySecondTab, oneHourSecondTab, forwardSecondTab,
				pauseSecondTab, backwardSecondTab, labelPeriodDaySecondTab,
				labelPeriodHourSecondTab);
		secondTab.setContent(paneForInsideTeperature);
		System.gc();
	}

	private void setScale(int scale, NumberAxis xAxis, NumberAxis yAxis) {
		if (scale == ONE_DAY) {
			xAxis.setLowerBound(0);
			xAxis.setUpperBound(24);
			xAxis.setTickUnit(1);

			yAxis.setUpperBound(50);
			yAxis.setLowerBound(-30);
			yAxis.setTickUnit(5);
		}
		if (scale == ONE_HOUR) {
			xAxis.setLowerBound(0);
			xAxis.setUpperBound(59);
			xAxis.setTickUnit(5);

			yAxis.setUpperBound(50);
			yAxis.setLowerBound(-30);
			yAxis.setTickUnit(5);
		}
	}

	@SuppressWarnings("deprecation")
	public Number getTime(Date date, int scale) {

		if (scale == ONE_DAY)
			return ((float) date.getHours() + 1.0F / 60.0F * (float) date
					.getMinutes());
		else if (scale == ONE_HOUR) {
			return date.getMinutes();
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	void activateForwardButtons() {
		if (((getDisplayHour() == getCurrentValueOfTheData().getCurrentDate()
				.getHours())
				&& (getDisplayingDay().getDate() == getCurrentValueOfTheData()
						.getCurrentDate().getDate() && displayingOneHour == true) || (getDisplayingDay()
				.getDate() == getCurrentValueOfTheData().getCurrentDate()
				.getDate() && displayingOneDay == true))) {
			forwardThirdTab.setDisable(true);
			forwardSecondTab.setDisable(true);
		} else {
			forwardThirdTab.setDisable(false);
			forwardSecondTab.setDisable(false);
		}
	}

	public class UpdateGUI implements Runnable {
		@Override
		public void run() {

			while (true) {
				if (!pause) {
					try {
						Thread.sleep(60000);
						Platform.runLater(new Runnable() {
							public void run() {
								main = main.refreshAllData();
								setTemperature(getCurrentValueOfTheData());
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public CreateConnectionDB getDb() {
		return db;
	}

	public void setDb(CreateConnectionDB db) {
		this.db = db;
	}

	public ArrayList<TemperatureInTheHouse> getTemperatureForOneHour(int hour) {
		ArrayList<TemperatureInTheHouse> temp = new ArrayList<TemperatureInTheHouse>();
		temp = db.getCurrentDayData();
		return temp;
	}

	public TemperatureInTheHouse getCurrentValueOfTheData() {
		return currentValueOfTheData;
	}

	public void getTemperatureLastHour() {
		hour = db.getCurrentHourData();
	}

	public void getTemperatureLastDay() {
		TemperatureInTheHouse t;
		day = new ArrayList<TemperatureInTheHouse>();
		for (int i = 0; i < db.getCurrentDayData().size(); i++) {
			t = db.getCurrentDayData().get(i);
			if (i % 10 == 0) {
				day.add(t);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void getPreviousHour() {
		Date temp = new Date(displayingDay.getTime());
		temp.setHours(displayingHour - 1);
		displayingHour = temp.getHours();

		db.setCurrentHourData(temp);
		hour = db.getCurrentHourData();
	}

	@SuppressWarnings("deprecation")
	public void getNextHour() {
		Date temp = new Date(displayingDay.getTime());
		temp.setHours(displayingHour + 1);
		displayingHour = temp.getHours();

		db.setCurrentHourData(temp);
		hour = db.getCurrentHourData();
	}

	@SuppressWarnings("deprecation")
	public void getPreviousDay() {
		Date searchDate = new Date(displayingDay.getTime());
		searchDate.setDate(displayingDay.getDate() - 1);
		displayingDay = new Date(searchDate.getTime());
		try {
			db.setSearchDayData(displayingDay);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		day = new ArrayList<TemperatureInTheHouse>();
		for (int i = 0; i < db.getSearchDayData().size(); i++) {
			if (i % 10 == 0) {
				day.add(db.getSearchDayData().get(i));
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void getNextDay() {
		Date searchDate = new Date(displayingDay.getTime());
		searchDate.setDate(displayingDay.getDate() + 1);
		displayingDay = new Date(searchDate.getTime());
		try {
			db.setSearchDayData(displayingDay);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		day = new ArrayList<TemperatureInTheHouse>();
		for (int i = 0; i < db.getSearchDayData().size(); i++) {
			if (i % 10 == 0) {
				day.add(db.getSearchDayData().get(i));
			}
		}
	}

	public ArrayList<TemperatureInTheHouse> getDay() {
		return day;
	}

	public ArrayList<TemperatureInTheHouse> getHour() {
		return hour;
	}

	public int getDisplayHour() {
		return displayingHour;
	}

	public Date getDisplayingDay() {
		return displayingDay;
	}

	public void setCurrentDate() {
		displayingDay = currentValueOfTheData.getCurrentDate();
	}

	@SuppressWarnings("deprecation")
	public void setCurrentHour() {
		displayingHour = currentValueOfTheData.getCurrentDate().getHours();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
