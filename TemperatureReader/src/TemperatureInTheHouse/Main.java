package TemperatureInTheHouse;
	
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import util.CreateExcelDoc;
import util.TestJDBC;
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
	private TestJDBC db = new TestJDBC();
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
//			currentValueOfTheData = new TemperatureInTheHouse(doc.readLineFromExcel(doc.getCountOfLines()));
			
			try {
				db.setConnection();
				db.setLastRecord();
				displayingHour = db.getCurrentDay().getHours();
				displayingDay = db.getCurrentDay();
				db.setCurrentDayData();
				db.setCurrentHourData(displayingDay);
				currentValueOfTheData = db.getLastRecord();
				
				
				System.out.println("!!!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
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
	    temp = db.getCurrentDayData();
	    return temp;
	}
	public TemperatureInTheHouse getCurrentValueOfTheData(){
	    return currentValueOfTheData;
	}
	
	public void getTemperatureLastHour(){
	    hour = db.getCurrentHourData();
	}
	
	public void getTemperatureLastDay(){
	    TemperatureInTheHouse t;
	    day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < db.getCurrentDayData().size() ; i++) {
		    t = db.getCurrentDayData().get(i);
		    if(i%10 == 0){
		    	day.add(t);
		    }
	    }
	}
	
	@SuppressWarnings("deprecation") 
	public void getPreviousHour(){
		Date temp = new Date(displayingDay.getTime());
		temp.setHours(displayingHour-1);
		displayingHour = temp.getHours();	
		
		db.setCurrentHourData(temp);
	    hour = db.getCurrentHourData();
	}
	
	@SuppressWarnings("deprecation")
	public void getNextHour(){
		Date temp = new Date(displayingDay.getTime());
		temp.setHours(displayingHour+1);
		displayingHour = temp.getHours();	
		
		db.setCurrentHourData(temp);
	    hour = db.getCurrentHourData();
	}
	
	@SuppressWarnings("deprecation")	
	public void getPreviousDay(){
		Date searchDate = new Date(displayingDay.getTime());
		searchDate.setDate(displayingDay.getDate()-1);
		displayingDay = new Date(searchDate.getTime());
		try {
			db.setSearchDayData(displayingDay);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < db.getSearchDayData().size() ; i++) {
	    	if(i%10 == 0){
				day.add(db.getSearchDayData().get(i));
			}
	    }
	    
	}
	
	@SuppressWarnings("deprecation")
	public void getNextDay(){
		Date searchDate = new Date(displayingDay.getTime());
		searchDate.setDate(displayingDay.getDate()+1);
		displayingDay = new Date(searchDate.getTime());
		try {
			db.setSearchDayData(displayingDay);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		day = new ArrayList<TemperatureInTheHouse>();
	    for (int i = 0; i < db.getSearchDayData().size() ; i++) {
	    	if(i%10 == 0){
				day.add(db.getSearchDayData().get(i));
			}
	    }
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
//		    currentValueOfTheData = new TemperatureInTheHouse(doc.readLineFromExcel(doc.getCountOfLines()));
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
