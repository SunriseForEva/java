package TemperatureInTheHouse;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import util.CreateExcelDoc;
import TemperatureInTheHouse.model.TemperatureInTheHouse;
import TemperatureInTheHouse.view.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import jxl.read.biff.BiffException;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private CreateExcelDoc doc;
	private TemperatureInTheHouse currentValueOfTheData ;
	static int count  = 0;
	private ArrayList<TemperatureInTheHouse> day = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> hour = new ArrayList<TemperatureInTheHouse>();
	
	int displayingHour;
	Date displayingDay;
	MainWindowController controller;
	
	
	@Override
	public void start(Stage primaryStage) {
    		currentValueOfTheData = new TemperatureInTheHouse();
    		this.primaryStage = primaryStage;
    		this.primaryStage.setTitle("Температура воздуха дома и на улице");
    		this.primaryStage.getIcons().add(new Image("resources/weather.png"));
    		initRootLayout();
    		showMainOverview();
	}
	
	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMainOverview(){
		try {
		    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainWindowLayout.fxml"));
		
			AnchorPane overview = (AnchorPane) loader.load();
			rootLayout.setCenter(overview);
			
			controller = loader.getController();

			doc = new CreateExcelDoc(CreateExcelDoc.READ);
			currentValueOfTheData = new TemperatureInTheHouse(doc.readLineFromExcel(doc.getCountOfLines()));
			
			setCurrentDate();
			setCurrentHour();
			getTemperatureLastDay();
			getTemperatureLastHour();
			
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (BiffException e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<TemperatureInTheHouse> getTemperatureForOneHour(int hour){
	    ArrayList<TemperatureInTheHouse> temp = new ArrayList<TemperatureInTheHouse>();
	    TemperatureInTheHouse t = new TemperatureInTheHouse();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.isHoursEqals(hour)){
			temp.add(t);
			if(temp.size() >= 61)
			    break;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    System.out.println(temp.size());
	    return temp;
	}
	public TemperatureInTheHouse getCurrentValueOfTheData(){
	    return currentValueOfTheData;
	}
	
	@SuppressWarnings("deprecation")
	public void getTemperatureLastHour(){
	    TemperatureInTheHouse t = new TemperatureInTheHouse();
	    hour = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    Date date = currentValueOfTheData.getCurrentDate();
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getDate() == date.getDate()&&
			    t.getCurrentDate().getHours() == date.getHours()
			    ){
			
			hour.add(t);
			if(hour.size() >= 59)
			    break;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void getTemperatureLastDay(){
	    TemperatureInTheHouse t;
	    day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getDate() == displayingDay.getDate() && i%10 == 0){
			day.add(t);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void getPreviousHour(){
		TemperatureInTheHouse t;
		--displayingHour;
	    hour = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getDate() == displayingDay.getDate()&&
			    t.getCurrentDate().getHours() == displayingHour)
		    {
				hour.add(t);
				if(hour.size() >= 59) break;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void getNextHour(){
		TemperatureInTheHouse t;
		++displayingHour;
	    hour = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getDate() == displayingDay.getDate()&&
			    t.getCurrentDate().getHours() == displayingHour)
		    {
				hour.add(t);
				if(hour.size() >= 59) break;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	
	@SuppressWarnings("deprecation")
	public void getPreviousDay(){
		TemperatureInTheHouse t;
		displayingDay = new Date(displayingDay.getYear(),displayingDay.getMonth(),displayingDay.getDate()-1);
		System.out.println(displayingDay);
	    day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getYear() == displayingDay.getYear() &&
		    	t.getCurrentDate().getMonth() == displayingDay.getMonth() &&
		    	t.getCurrentDate().getDate() == displayingDay.getDate())
		    {
		    	if(t.getCurrentDate().getDate() == displayingDay.getDate() && i%10 == 0){
					day.add(t);
				}
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    System.out.println(day.size());
	}
	
	@SuppressWarnings("deprecation")
	public void getNextDay(){
		TemperatureInTheHouse t;
		displayingDay = new Date(displayingDay.getYear(),displayingDay.getMonth(),displayingDay.getDate() + 1);
		System.out.println(displayingDay);
	    day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < doc.getCountOfLines() ; i++) {
		try {
		    t = new TemperatureInTheHouse(doc.readLineFromExcel(i));
		    if(t.getCurrentDate().getYear() == displayingDay.getYear() &&
		    	t.getCurrentDate().getMonth() == displayingDay.getMonth() &&
		    	t.getCurrentDate().getDate() == displayingDay.getDate())
		    {
		    	if(t.getCurrentDate().getDate() == displayingDay.getDate() && i%10 == 0){
					day.add(t);
				}
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    System.out.println(day.size());
	}
	
	public ArrayList<TemperatureInTheHouse> getDay(){
	    return day;
	}
	
	public ArrayList<TemperatureInTheHouse> getHour(){
	    return hour;
	}
	public int getDisplayHour(){
		return displayingHour;
	}
	public Date getDisplayingDay(){
		return displayingDay;
	}
	
	public void setCurrentDate(){
		displayingDay = currentValueOfTheData.getCurrentDate();
	}
	
	@SuppressWarnings("deprecation")
	public void setCurrentHour(){
		displayingHour = currentValueOfTheData.getCurrentDate().getHours();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public Main refreshAllData(){ 
		System.gc();
	    try{
		    doc = new CreateExcelDoc(CreateExcelDoc.READ);
		    currentValueOfTheData = new TemperatureInTheHouse(doc.readLineFromExcel(doc.getCountOfLines()));
		    setCurrentDate();
		    setCurrentHour();
		    getTemperatureLastDay();
		    getTemperatureLastHour();
		    } catch (IOException|BiffException e) {
			e.printStackTrace();
		    }
	    System.gc();
	    return this;
	}
	
}
