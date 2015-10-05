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
	
//	private String URL = "jdbc:mysql://31.129.67.21:3306/temperature";
//	private String user = "slava";
//	private String password = "slava7777777";
	
	private String URL = "jdbc:mysql://localhost/sunrise";
	private String user = "sunrise";
	private String password = "777";
	
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
    
    
    public Date getCurrentDay() {
		return currentDay;
	}


	public TemperatureInTheHouse getLastRecord() {
		return lastRecord.get(0);
	}
    
    private void setCurrentDayData() throws SQLException{
/*Получаем строковое представление текущей даты(private Date currentDate) для формирования
 *  SQL-запроса получающий из базы все сегодняшние(последие сутки в базе)значения*/
    	ResultSet rsRecPerDay = stmt.executeQuery(requestSearchDate+"'"+getStingFromDate(currentDay)+"'");
        getDataFromRequest(rsRecPerDay, currentDayData);
    }
    
    private void setSearchDayData(Date searchingDate) throws SQLException{
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
    		record.add(rs.getFloat("t_balconyWest"));
    		record.add(rs.getFloat("t_bedroom"));
    		record.add(rs.getFloat("t_mainRoom"));
    		record.add(rs.getFloat("t_balconyEast"));
    		record.add(rs.getFloat("t_childrenroom"));
    		record.add(rs.getFloat("t_hall"));
    		record.add(rs.getFloat("t_kitchen"));
    		record.add(rs.getFloat("t_outerYard"));
    		record.add(rs.getFloat("t_outerForest"));
    		record.add(rs.getFloat("t_water"));
    		record.add(rs.getFloat("t_pantry"));
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
	
	private static String getStingFromDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/*All data is read from list of Temperature */
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
	
	public void showAllRecordPerSearchingDey(){
		Iterator<TemperatureInTheHouse> iter = searchDayData.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
    public static void main(String[] args) throws SQLException {
    	double[] arr = new double[5];
    	
    	for (int i = 0; i < 5 ; i++) {
    		double begin = System.currentTimeMillis();
    		
        	TestJDBC tj = new TestJDBC();
    		tj.setConnection();
    		tj.setLastRecord();
    		tj.setSearchDayData(getDateFromString("2015-10-01 00:00:00"));
//    		tj.showAllRecordPerSearchingDey();    	

    		double end = System.currentTimeMillis();
    		arr[i] = (end-begin)/(double)1000;
		}
    	
		
		for (double d : arr) {
			System.out.print( d + " ");
		}
    	
    }
}
