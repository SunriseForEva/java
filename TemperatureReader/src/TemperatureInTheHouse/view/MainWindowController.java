package TemperatureInTheHouse.view;

import java.util.ArrayList;
import java.util.Date;

import TemperatureInTheHouse.Main;
import TemperatureInTheHouse.model.TemperatureInTheHouse;
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

public class MainWindowController {
    private TemperatureInTheHouse temperature;
	private  Main main ;
	private static int ONE_HOUR = 60;
	private static int ONE_DAY = 24;
	boolean displayingOneHour;
	boolean displayingOneDay;
	boolean pause;
	
	XYChart.Series<Number, Number> balcony2;
	XYChart.Series<Number, Number> balcony1;
	XYChart.Series<Number, Number> kitchen ;
	XYChart.Series<Number, Number> childroom ;
	XYChart.Series<Number, Number> hall ;
	XYChart.Series<Number, Number> badroom ;
	XYChart.Series<Number, Number> hallway ;
	XYChart.Series<Number, Number> pantry ;
	XYChart.Series<Number, Number> forest ;
	XYChart.Series<Number, Number> outerYard ;
	
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
	final NumberAxis xAxisOut = new NumberAxis(0,0,0);
	@FXML
	final NumberAxis yAxisOut = new NumberAxis(0,0,0);
	@FXML
	final NumberAxis xAxisIn = new NumberAxis(0,0,0);
	@FXML
	final NumberAxis yAxisIn = new NumberAxis(0,0,0);
	
	@FXML
	private AreaChart<Number, Number> temperatureInside = new  AreaChart<Number, Number>(xAxisIn, yAxisIn) ;
	@FXML
	private AreaChart<Number, Number> temperatureOutside= new  AreaChart<Number, Number>(xAxisOut, yAxisOut) ;;
	
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
		
	}
	
	@FXML
	public void initialize(){
		displayingOneHour = true;
		pause = false;
		
		labelPeriodDaySecondTab = new Label();
		labelPeriodDaySecondTab.setLayoutX(3);
		labelPeriodDaySecondTab.setAlignment(Pos.CENTER);
		labelPeriodDaySecondTab.setLayoutY(400);
		labelPeriodDaySecondTab.setMinSize(105, 20);
		
		labelPeriodHourSecondTab = new Label();
		labelPeriodHourSecondTab.setLayoutX(3);
		labelPeriodHourSecondTab.setAlignment(Pos.CENTER);
		labelPeriodHourSecondTab.setLayoutY(415);
		labelPeriodHourSecondTab.setMinSize(105, 20);
		
		labelPeriodDayThirdTab = new Label();
		labelPeriodDayThirdTab.setLayoutX(3);
		labelPeriodDayThirdTab.setAlignment(Pos.CENTER);
		labelPeriodDayThirdTab.setLayoutY(400);
		labelPeriodDayThirdTab.setMinSize(105, 20);
		
		labelPeriodHourThirdTab = new Label();
		labelPeriodHourThirdTab.setLayoutX(3);
		labelPeriodHourThirdTab.setAlignment(Pos.CENTER);
		labelPeriodHourThirdTab.setLayoutY(415);
		labelPeriodHourThirdTab.setMinSize(105, 20);
		
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
		temperatureOutside = new AreaChart<Number,Number>(xAxisOut, yAxisOut);
		
		setScale(ONE_HOUR, xAxisIn, yAxisIn);
		temperatureInside = new AreaChart<Number, Number>(xAxisIn, yAxisIn);
		
		oneDaySecondTab = new Button("24 часа");
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
			showDisplayTemperatureOutside(main.getDay(), ONE_DAY);
			labelPeriodDaySecondTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
				((main.getDisplayingDay().getMonth() ) +1)+ " . " + 
				 (main.getDisplayingDay().getYear()+1900));
			labelPeriodHourSecondTab.setText("c 00:00 до 24:00 ");
			System.gc();
		    }
		});
		
		oneHourSecondTab = new Button("1 час");
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
			showDisplayTemperatureOutside(main.getHour(),ONE_HOUR);
			labelPeriodDaySecondTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
				((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
				(main.getDisplayingDay().getYear()+1900));
			labelPeriodHourSecondTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
			System.gc();
		    }
		});
		
		forwardSecondTab = new Button(">");
		forwardSecondTab.setLayoutX(67);
		forwardSecondTab.setLayoutY(475);
		forwardSecondTab.setMinSize(25,26);
		forwardSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneHour == true){
					main.getNextHour();
					showDisplayTemperatureOutside(main.getHour(),ONE_HOUR);
					labelPeriodDaySecondTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourSecondTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
				}
				if(displayingOneDay == true){
					main.getNextDay();
					showDisplayTemperatureOutside(main.getDay(),ONE_DAY);
					labelPeriodDaySecondTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourSecondTab.setText("c 00:00 до 24:00 ");
				}
				System.gc();
			}
		});
		
		pauseSecondTab = new Button("II");
		pauseSecondTab.setLayoutX(38);
		pauseSecondTab.setLayoutY(475);
		pauseSecondTab.setMinSize(25,26);
		pauseSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(pause == false){
					pause = true;
					pauseThirdTab.setText("->");
					pauseSecondTab.setText("->");
					System.out.println(pause);
				}
				else{
					pause = false;
					pauseThirdTab.setText("II");
					pauseSecondTab.setText("II");
					System.out.println(pause);
				}
			}
		});
		
		backwardSecondTab = new Button("<");
		backwardSecondTab.setLayoutX(9);
		backwardSecondTab.setLayoutY(475);
		backwardSecondTab.setMinSize(25,26);
		backwardSecondTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneHour == true){
					main.getPreviousHour();
					showDisplayTemperatureOutside(main.getHour(),ONE_HOUR);
					labelPeriodDaySecondTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourSecondTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
				}
				if(displayingOneDay == true){
					main.getPreviousDay();
					showDisplayTemperatureOutside(main.getDay(),ONE_DAY);
					labelPeriodDaySecondTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
						((main.getDisplayingDay().getMonth() ) +1) + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourSecondTab.setText("c 00:00 до 24:00 ");
				}
				System.gc();
			}
		});
		
		temperatureInTheForest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureOutside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureOutside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheOuterYard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureOutside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureOutside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		oneDayThirdTab = new Button("24 часа");
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
			showDisplayTemperatureInside(main.getDay(), ONE_DAY);
			labelPeriodDayThirdTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
				((main.getDisplayingDay().getMonth() ) +1)+ " . " + 
				 (main.getDisplayingDay().getYear()+1900));
			labelPeriodHourThirdTab.setText("c 00:00 до 24:00 ");
			System.gc();
		    }
		});
		
		oneHourThirdTab = new Button("1 час");
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
			showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
			labelPeriodDayThirdTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
				((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
				(main.getDisplayingDay().getYear()+1900));
			labelPeriodHourThirdTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
			System.gc();
		    }
		});
		
		forwardThirdTab = new Button(">");
		forwardThirdTab.setLayoutX(67);
		forwardThirdTab.setLayoutY(475);
		forwardThirdTab.setMinSize(25,26);
		forwardThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneHour == true){
					main.getNextHour();
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
					labelPeriodDayThirdTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourThirdTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
				}
				if(displayingOneDay == true){
					main.getNextDay();
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
					labelPeriodDayThirdTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourThirdTab.setText("c 00:00 до 24:00 ");
				}
				System.gc();
			}
		});
//------------------------------------------------------------------------------
		pauseThirdTab = new Button("II");
		pauseThirdTab.setLayoutX(38);
		pauseThirdTab.setLayoutY(475);
		pauseThirdTab.setMinSize(25,26);
		pauseThirdTab.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(pause == false){
					pause = true;
					pauseThirdTab.setText("->");
					pauseSecondTab.setText("->");
					System.out.println(pause);
				}
				else{
					pause = false;
					pauseThirdTab.setText("II");
					pauseSecondTab.setText("II");
					System.out.println(pause);
				}
			}
		});
//------------------------------------------------------------------------------
		backwardThirdTab = new Button("<");
		backwardThirdTab.setLayoutX(9);
		backwardThirdTab.setLayoutY(475);
		backwardThirdTab.setMinSize(25,26);
		backwardThirdTab.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneHour == true){
					main.getPreviousHour();
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
					labelPeriodDayThirdTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
						((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourThirdTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
				}
				if(displayingOneDay == true){
					main.getPreviousDay();
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
					labelPeriodDayThirdTab.setText("" + main.getDisplayingDay().getDate()+ " . " +
						((main.getDisplayingDay().getMonth() ) +1) + " . " + 
						(main.getDisplayingDay().getYear()+1900));
					labelPeriodHourThirdTab.setText("c 00:00 до 24:00 ");
				}
				System.gc();
			}
		});
		
		temperatureInTheBadRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheBalcony1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheBalcony2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheChildRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheHall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheHallWay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheKitchen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInTheBadRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
		
		temperatureInThePantry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.gc();
				if(displayingOneDay == true){
					showDisplayTemperatureInside(main.getDay(),ONE_DAY);
				}
				if(displayingOneHour == true){
					showDisplayTemperatureInside(main.getHour(),ONE_HOUR);
				}
				System.gc();
			}
		});
	}
	
	public void setMainApp(Main main){
			this.main = main;
			setTemperature(main.getCurrentValueOfTheData());
			
			showDisplayTemperatureInside(this.main.getHour(), ONE_HOUR);
			showDisplayTemperatureOutside(this.main.getHour(),ONE_HOUR);
			
			Thread update = new Thread(new UpdateGUI());
			update.setDaemon(true);
			update.start();
	}
	
	public void setTemperature(TemperatureInTheHouse t){
		temperature = t;
		textFlow1.setText(String.valueOf(temperature.getBalcony_1()));
		textFlow2.setText(String.valueOf(temperature.getBadRoom()));
		textFlow3.setText(String.valueOf(temperature.getHall()));
		textFlow4.setText(String.valueOf(temperature.getBalcony_2()));
		textFlow5.setText(String.valueOf(temperature.getChildRoom()));
		textFlow6.setText(String.valueOf(temperature.getHallWay()));
		textFlow7.setText(String.valueOf(temperature.getKitchen()));
		textFlow8.setText(String.valueOf(temperature.getPantry()));
		textFlow9.setText(String.valueOf(temperature.getCurrentDate()));
	}
	@SuppressWarnings({ "unchecked", "deprecation"})
	public void showDisplayTemperatureInside(ArrayList<TemperatureInTheHouse> outsideArray,int scale){
		activateForwardButtons();
		
		labelPeriodDaySecondTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
			((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
			(main.getDisplayingDay().getYear()+1900));
		labelPeriodHourSecondTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");

		balcony2 = new XYChart.Series<Number, Number>();
		balcony1 = new XYChart.Series<Number, Number>();
		kitchen = new XYChart.Series<Number, Number>();
		childroom = new XYChart.Series<Number, Number>();
		hall = new XYChart.Series<Number, Number>();
		badroom = new XYChart.Series<Number, Number>();
		hallway = new XYChart.Series<Number, Number>();
		pantry = new XYChart.Series<Number, Number>();
		
		balcony1.setName("Балкон1");
		balcony2.setName("Балкон2");
		kitchen.setName("Кухня");
		childroom.setName("Детская");
		hall.setName("Зал");
		badroom.setName("Спальня");
		hallway.setName("Коридор");
		pantry.setName("Кладовая");
		
		for (int i = 0; i < outsideArray.size(); i++) {
			if(temperatureInTheBalcony1.isSelected()){
				balcony1.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getBalcony_1()));
			}
			if(temperatureInTheBalcony2.isSelected()){
				balcony2.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getBalcony_2()));
			}
			if(temperatureInTheKitchen.isSelected()){
				kitchen.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getKitchen()));
			}
			if(temperatureInTheChildRoom.isSelected()){
				childroom.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getChildRoom()));
			}
			if(temperatureInTheHall.isSelected()){
				hall.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getHall()));
			}
			if(temperatureInTheBadRoom.isSelected()){
				badroom.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getBadRoom()));
			}
			if(temperatureInTheHallWay.isSelected()){
				hallway.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getHallWay()));
			}
			if(temperatureInThePantry.isSelected()){
				pantry.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getPantry()));
			}
		}
		
		temperatureInside.getData().clear();
		temperatureInside.getData().addAll(balcony1, balcony2, kitchen, childroom, hallway, hall, badroom, pantry);
		temperatureInside.setPrefHeight(640);
		temperatureInside.setPrefWidth(722);
		temperatureInside.setLayoutX(80);
		temperatureInside.setLayoutY(6);
		
		paneForInsideTeperature = new Pane(temperatureInside,temperatureInTheBalcony1,temperatureInTheBalcony2,
				temperatureInTheBadRoom,temperatureInTheHall,temperatureInTheChildRoom,
				temperatureInTheHallWay,temperatureInTheKitchen,temperatureInThePantry, oneDayThirdTab, oneHourThirdTab,
				forwardThirdTab, pauseThirdTab, backwardThirdTab,
				labelPeriodDayThirdTab, labelPeriodHourThirdTab);
		thirdTab.setContent(paneForInsideTeperature);
		System.gc();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void showDisplayTemperatureOutside(ArrayList<TemperatureInTheHouse> outsideArray,int scale){
		activateForwardButtons();

		labelPeriodDayThirdTab.setText("" + (main.getDisplayingDay().getDate())+ " . " +
			((main.getDisplayingDay().getMonth() ) +1)  + " . " + 
			(main.getDisplayingDay().getYear()+1900));
		labelPeriodHourThirdTab.setText("c " + main.getDisplayHour()+ " : 00 до " + (main.getDisplayHour() + 1) + " : 00");
		
		forest = new XYChart.Series<Number, Number>();
		outerYard = new XYChart.Series<Number, Number>();
				
		forest.setName("Запад");
		outerYard.setName("Восток");

		for (int i = 0; i < outsideArray.size(); i++) {
			if(temperatureInTheForest.isSelected()){
				forest.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getOuterForest()));
			}
			if(temperatureInTheOuterYard.isSelected()){
				outerYard.getData().add(new XYChart.Data<Number, Number>(getTime(outsideArray.get(i).getCurrentDate(),scale),outsideArray.get(i).getOuterYard()));
			}
		}
		
		temperatureOutside.getData().clear();
		temperatureOutside.getData().addAll(forest,outerYard);
		temperatureOutside.setPrefHeight(640);
		temperatureOutside.setPrefWidth(722);
		temperatureOutside.setLayoutX(80);
		temperatureOutside.setLayoutY(6);
		
		paneForInsideTeperature = new Pane(temperatureOutside,temperatureInTheForest,temperatureInTheOuterYard,
				oneDaySecondTab, oneHourSecondTab, forwardSecondTab, pauseSecondTab, backwardSecondTab,
				labelPeriodDaySecondTab,labelPeriodHourSecondTab);
		secondTab.setContent(paneForInsideTeperature);
		System.gc();
	}
	
	private void setScale(int scale , NumberAxis xAxis, NumberAxis yAxis){
	    if (scale == ONE_DAY){
	    	xAxis.setLowerBound(0);
	    	xAxis.setUpperBound(24);
	    	xAxis.setTickUnit(1);
		
	    	yAxis.setUpperBound(50);
			yAxis.setLowerBound(-30);
			yAxis.setTickUnit(5);
	    }
	    if(scale == ONE_HOUR){
	    	xAxis.setLowerBound(0);
	    	xAxis.setUpperBound(59);
	    	xAxis.setTickUnit(5);
		
	    	yAxis.setUpperBound(50);
	    	yAxis.setLowerBound(-30);
	    	yAxis.setTickUnit(5);
	    }
	}
	
	@SuppressWarnings("deprecation")
	public Number getTime(Date date,int scale){// получаю представление времени(одного часа) в виде десятичного 
	    if(scale == ONE_DAY)		   // числа от 0 до 10
		return ((float)date.getHours() + 1.0F/60.0F*(float)date.getMinutes());
	    else if(scale == ONE_HOUR){
		return date.getMinutes();
	    }
	    return 0;
	}
	@SuppressWarnings("deprecation")
	void activateForwardButtons(){
		if((main.getDisplayHour() == main.getCurrentValueOfTheData().getCurrentDate().getHours() && displayingOneHour == true) ||
				(main.getDisplayingDay().getDate() == main.getCurrentValueOfTheData().getCurrentDate().getDate() && displayingOneDay ==true))
		{
			forwardThirdTab.setDisable(true);
			forwardSecondTab.setDisable(true);
		}
		else
		{
			forwardThirdTab.setDisable(false);
			forwardSecondTab.setDisable(false);
		}
	}
	
	public class UpdateGUI implements Runnable {
	    @Override
	    public void run() {
	    
		while(true){
			if(!pause){
			    try {
				    Thread.sleep(60000);
	        		    Platform.runLater(new Runnable() {
	        			public void run() {
	        			    main = main.refreshAllData();
	        			    setTemperature(main.getCurrentValueOfTheData());
	        			}
	        	});
			    } catch (InterruptedException e) {
			    	e.printStackTrace();
			    }
			}
	    }
	    } 
	}
}
