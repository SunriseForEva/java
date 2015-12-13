package util;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;

import TemperatureInTheHouse.model.TemperatureInTheHouse;

public class TestJDBC {
	

	private ArrayList<TemperatureInTheHouse> lastRecord = new ArrayList<TemperatureInTheHouse>();
	
	private ArrayList<TemperatureInTheHouse> currentHourData = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> currentDayData = new ArrayList<TemperatureInTheHouse>();
	
	private ArrayList<TemperatureInTheHouse> searchHourData = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> searchDayData = new ArrayList<TemperatureInTheHouse>();
	
//	private String URL = "jdbc:mysql://178.219.93.93:3306/myDb";
//	private String user = "sunrise4eva";
//	private String password = "sunrisesql1982";
	
	private String URL = "jdbc:mysql://31.129.67.21:3306/temperature";
	private String user = "slava";
	private String password = "slava7777777";
	
	/*private String URL = "jdbc:mysql://localhost/sunrise";
	private String user = "sunrise";
	private String password = "777";*/
	
    private Connection con = null;
    private Statement stmt = null;
    
    Date currentDay = null ;

    private String requestSearchDate = "SELECT *FROM housestemperatures "+
    "WHERE DATE(currentTime)=";
    private String requestLastRecord = "SELECT *FROM housestemperatures "+
    "ORDER BY id DESC LIMIT 1";
    
    /*Set connection to MySql server*/
    public void setConnection() throws SQLException{ 
    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    	con = DriverManager.getConnection(URL, user, password);
    	if(con != null){
    	    System.out.println("Ok!");
    	}
    	stmt = con.createStatement();
    }
    
    
    public ArrayList<TemperatureInTheHouse> getCurrentDayData() {
		return currentDayData;
	}


	public ArrayList<TemperatureInTheHouse> getCurrentHourData() {
		return currentHourData;
	}
	

	public ArrayList<TemperatureInTheHouse> getSearchDayData() {
		return searchDayData;
	}


	public Date getCurrentDay() {
		return currentDay;
	}


	public TemperatureInTheHouse getLastRecord() {
		return lastRecord.get(0);
	}
    
    public void setCurrentDayData() throws SQLException{
/*Получаем строковое представление текущей даты(private Date currentDate) для формирования
 *  SQL-запроса получающий из базы все сегодняшние(последие сутки в базе)значения*/
    	ResultSet rsRecPerDay = stmt.executeQuery(requestSearchDate+"'"+getStingFromDate(currentDay)+"'");
        getDataFromRequest(rsRecPerDay, currentDayData);
    }
    
    public void setSearchDayData(Date searchingDate) throws SQLException{
    	searchDayData = new ArrayList<TemperatureInTheHouse>();
    	ResultSet rsRecPerDay = stmt.executeQuery(requestSearchDate+"'"+getStingFromDate(searchingDate)+"'");
        getDataFromRequest(rsRecPerDay, searchDayData);
    }
    
    /* Last record get from table and set current(date of last record)*/
    public void setLastRecord() throws SQLException{ 
    	ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
    	getDataFromRequest(rsLastRec,lastRecord);
    	currentDay = lastRecord.get(0).getCurrentDate();
    }
    
    /*   all data read from request and put it into object Temperature */
	private void getDataFromRequest(ResultSet rs, ArrayList<TemperatureInTheHouse> listTemperature) throws SQLException { 
		while( rs.next() ){   
    		ArrayList<Object> record = new ArrayList<Object>();
    		record.add(rs.getDouble("t_balconyWest"));
    		record.add(rs.getDouble("t_bedroom"));
    		record.add(rs.getDouble("t_mainRoom"));
    		record.add(rs.getDouble("t_balconyEast"));
    		record.add(rs.getDouble("t_childrenroom"));
    		record.add(rs.getDouble("t_hall"));
    		record.add(rs.getDouble("t_kitchen"));
    		record.add(rs.getDouble("t_outerYard"));
    		record.add(rs.getDouble("t_outerForest"));
    		record.add(rs.getDouble("t_water"));
    		record.add(rs.getDouble("t_pantry"));
    		record.add(getDateFromString(rs.getString("currentTime")));
    		listTemperature.add(new TemperatureInTheHouse(record));
    	}
	}
			
	/*Take Date from String*/
	private static Date getDateFromString(String d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date temp = new Date();
		try {
			temp = sdf.parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return temp;
	}
	
	private static String getStingForDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	private static String getStingFromDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public void setCurrentHourData(Date currentDay){
		Date prevDate = new Date(currentDay.getTime());
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);
		
		System.out.println(getStingForDateTime(prevDate));
		
		Date nextDate = new Date(currentDay.getTime());
		nextDate.setMinutes(59);
		nextDate.setSeconds(59);

		System.out.println(getStingForDateTime(nextDate));
		
		String getCurrentHour = "SELECT *FROM housestemperatures "+
			    "WHERE currentTime<'" + getStingForDateTime(nextDate)+
			    "' && currentTime>'" + getStingForDateTime(prevDate) + "'";
		
		ResultSet rsLastRec;
		try {
			currentHourData = new ArrayList<TemperatureInTheHouse>();
			rsLastRec = stmt.executeQuery(getCurrentHour);
			getDataFromRequest(rsLastRec,currentHourData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	
/*	All data is read from list of Temperature 
	public void showLastRecord(){
		Iterator<TemperatureInTheHouse> iter = lastRecord.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	public void showAllRecordPerDey(){
		Iterator<TemperatureInTheHouse> iter = currentDayData.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	*/
	public void showAllRecordLastHour(){
		Iterator<TemperatureInTheHouse> iter = currentHourData.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().toString());
		}
	}
	
    public static void main(String[] args) throws SQLException {
        	TestJDBC tj = new TestJDBC();
    		tj.setConnection();
    		tj.setLastRecord();
    		tj.setCurrentHourData(tj.getCurrentDay());
    		tj.showAllRecordLastHour();    	
    }
}
