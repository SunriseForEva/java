package test.JDBC;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import test.JDBC.Temperature;

import java.util.ArrayList;

public class TestJDBC {
	private ArrayList<Temperature> lastRecord = new ArrayList<Temperature>();
	
	private ArrayList<Temperature> currentHourData = new ArrayList<Temperature>();
	private ArrayList<Temperature> currentDayData = new ArrayList<Temperature>();
	
	private ArrayList<Temperature> searchHourData = new ArrayList<Temperature>();
	private ArrayList<Temperature> searchDayData = new ArrayList<Temperature>();
	
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
    private void setLastRecord() throws SQLException{ 
    	ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
    	getDataFromRequest(rsLastRec,lastRecord);
    	currentDay = lastRecord.get(0).getCurrentDate();
    }
    
    /*   all data read from request and put it into object Temperature */
	private void getDataFromRequest(ResultSet rs, ArrayList<Temperature> listTemperature) throws SQLException { 
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
    		listTemperature.add(new Temperature(record));
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
		Iterator<Temperature> iter = lastRecord.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	public void showAllRecordPerDey(){
		Iterator<Temperature> iter = currentDayData.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	public void showAllRecordPerSearchingDey(){
		Iterator<Temperature> iter = searchDayData.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
    public static void main(String[] args) throws SQLException {
		TestJDBC tj = new TestJDBC();
    	
		tj.setConnection();
		tj.setLastRecord();
		tj.setSearchDayData(getDateFromString("2015-09-27 00:00:00"));
		tj.showAllRecordPerSearchingDey();    	
    	
		
    	
    }
}
