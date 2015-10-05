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
	private TestJDBC db;
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
				currentValueOfTheData = db.getLastRecord();
				System.out.println("!!!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	    catchWrongData(hour);
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
	    catchWrongData(day);
	}
	
	@SuppressWarnings("deprecation") 
	public void getPreviousHour(){
		TemperatureInTheHouse t;
		--displayingHour;			//дикремент текущего времени
		if(displayingHour == 0 || displayingHour == -1){			//если показываемый час равен 0 часов или (-1) устанвливаем время 23:00
			displayingHour =23 ;
			getPreviousDay();
		}
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
	    catchWrongData(hour);
	}
	
	@SuppressWarnings("deprecation")
	public void getNextHour(){
		TemperatureInTheHouse t;
		if(++displayingHour == 24){
			displayingHour = 0;
			getNextDay();
		}
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
	    catchWrongData(hour);
	}
	
	@SuppressWarnings("deprecation")	
	public void getPreviousDay(){
		TemperatureInTheHouse t;
		displayingDay = new Date(displayingDay.getYear(),displayingDay.getMonth(),displayingDay.getDate()-1);
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
	    catchWrongData(day);
	}
	
	@SuppressWarnings("deprecation")
	public void getNextDay(){
		TemperatureInTheHouse t;
		displayingDay = new Date(displayingDay.getYear(),displayingDay.getMonth(),displayingDay.getDate() + 1);
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
	    catchWrongData(day);
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
	
	public void catchWrongData(ArrayList<TemperatureInTheHouse> array){ //если значение с датчика некорректны(-127(не успел ответить)
		ArrayList<Double> temp = new ArrayList<Double>();				//или +85(нет питания на датчик)) заменить на среднее ариф. списка
		
	    double averageHall = 0.0D;
	    double averageChildRoom = 0.0D;
	    double averageKitchen = 0.0D;
	    double averageBadRoom = 0.0D;
	    double averageHallWay = 0.0D;
	    double averagePantry = 0.0D;                  //кладовая
	    double averageBalcony_1 = 0.0D;
	    double averageBalcony_2 = 0.0D;
	    double averageOuterForest = 0.0D;
	    double averageOuterYard = 0.0D;
	    
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getHall() > -50 && array.get(i).getHall() < 70){
				temp.add(array.get(i).getHall());
			}
		}
		averageHall = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getChildRoom() > -50 && array.get(i).getChildRoom() < 70){
				temp.add(array.get(i).getChildRoom());
			}
		}
		averageChildRoom = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getKitchen() > -50 && array.get(i).getKitchen() < 70){
				temp.add(array.get(i).getKitchen());
			}
		}
		averageKitchen = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getBadRoom() > -50 && array.get(i).getBadRoom() < 70){
				temp.add(array.get(i).getBadRoom());
			}
		}
		averageBadRoom = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getHallWay() > -50 && array.get(i).getHallWay() < 70){
				temp.add(array.get(i).getHallWay());
			}
		}
		averageHallWay = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getPantry() > -50 && array.get(i).getPantry() < 70){
				temp.add(array.get(i).getPantry());
			}
		}
		averagePantry = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getBalcony_1() > -50 && array.get(i).getBalcony_1() < 70){
				temp.add(array.get(i).getBalcony_1());
			}
		}
		averageBalcony_1 = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getBalcony_2() > -50 && array.get(i).getBalcony_2() < 70){
				temp.add(array.get(i).getBalcony_2());
			}
		}
		averageBalcony_2 = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getOuterYard() > -50 && array.get(i).getOuterYard() < 70){
				temp.add(array.get(i).getOuterYard());
			}
		}
		averageOuterYard = getAverage(temp);
		temp = new ArrayList<Double>();
		
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getOuterForest() > -50 && array.get(i).getOuterForest() < 70){
				temp.add(array.get(i).getOuterForest());
			}
		}
		averageOuterForest = getAverage(temp);
//---------------------Заменяем все некорректные значения средним арифметическим за заданный период--------------
		for (int i = 0; i < array.size(); i++) {
			if( array.get(i).getHall() < -50 || array.get(i).getHall() > 70){
				array.get(i).setHall(averageHall);
			}
			
			if( array.get(i).getChildRoom() < -50 || array.get(i).getChildRoom() > 70){
				array.get(i).setChildRoom(averageChildRoom);
			}
			
			if( array.get(i).getKitchen() < -50 || array.get(i).getKitchen() > 70){
				array.get(i).setKitchen(averageKitchen);
			}
			
			if( array.get(i).getBadRoom() < -50 || array.get(i).getBadRoom() > 70){
				array.get(i).setBadRoom(averageBadRoom);
			}
			
			if( array.get(i).getHallWay() < -50 || array.get(i).getHallWay() > 70){
				array.get(i).setHallWay(averageHallWay);
			}
			
			if( array.get(i).getPantry() < -50 || array.get(i).getPantry() > 70){
				array.get(i).setPantry(averagePantry);
			}
			
			if( array.get(i).getBalcony_1() < -50 || array.get(i).getBalcony_1() > 70){
				array.get(i).setBalcony_1(averageBalcony_1);
			}
			
			if( array.get(i).getBalcony_2() < -50 || array.get(i).getBalcony_2() > 70){
				array.get(i).setBalcony_2(averageBalcony_2);
			}
			
			if( array.get(i).getOuterYard() < -50 || array.get(i).getOuterYard() > 70){
				array.get(i).setOuterYard(averageOuterYard);
			}
			
			if( array.get(i).getOuterForest() < -50 || array.get(i).getOuterForest() > 70){
				array.get(i).setOuterForest(averageOuterForest);
			}
		}
	}
	
	public double getAverage(ArrayList<Double> temp){
		double total = 0.0D;
		for (int i = 0; i < temp.size(); i++) {
			total += temp.get(i);
		}
		
		return total/(double)temp.size();
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
