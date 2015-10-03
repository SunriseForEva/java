package test.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC {
	static String date = new String("wsdas");
	static int id;
	static float t_balconyEast ;
	static float t_bedroom ;
	static float t_hall ;
	static float t_balconyWest ;
	static float t_childrenroom ;
	static float t_kitchen ;
	static float t_pantry ;
	static float t_outerForest ;
	static float t_outerYard ;
	static float t_mainRoom ;
	static float t_water ;

	List<Object> lastRecord = new ArrayList();
	
	
    public static Connection con = null;
    public static Statement stmt = null;
    public static String request = "SELECT *FROM housestemperatures "+
    "WHERE currentTime>'2015-10-2 12:00:01'&&"+
    		"currentTime<'2015-10-2 13:00:01'";
    
    String requestLastRecord = "SELECT *FROM housestemperatures "+
    "ORDER BY id DESC LIMIT 1";
    
    void getLastRecord() throws SQLException{
    	
    	
    	stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(requestLastRecord);
    	getDataFromRequest(rs);
    	
    }
	public void getDataFromRequest(ResultSet rs) throws SQLException { //к аргументам функции добавить карту(map)
		while(rs.next()){
    		id = rs.getInt("ID");
    		date = rs.getString("currentTime");
    		t_balconyEast = rs.getFloat("t_balconyEast");
    		t_bedroom = rs.getFloat("t_bedroom");
    		t_hall = rs.getFloat("t_hall");
    		t_balconyWest = rs.getFloat("t_balconyWest");
    		t_childrenroom = rs.getFloat("t_childrenroom");
    		t_kitchen = rs.getFloat("t_kitchen");
    		t_pantry = rs.getFloat("t_pantry");
    		t_outerForest = rs.getFloat("t_outerForest");
    		t_outerYard = rs.getFloat("t_outerYard");
    		t_mainRoom  = rs.getFloat("t_mainRoom");
    		t_water = rs.getFloat("t_water");
    		
    		lastRecord.add(id);   // вместо ArrayList использовать TreeMap
    		lastRecord.add(date);
    		lastRecord.add(t_balconyEast);
    		lastRecord.add(t_bedroom);
    		lastRecord.add(t_hall);
    		lastRecord.add(t_balconyWest);
    		lastRecord.add(t_childrenroom);
    		lastRecord.add(t_kitchen);
    		lastRecord.add(t_pantry);
    		lastRecord.add(t_outerForest);
    		lastRecord.add(t_outerYard);
    		lastRecord.add(t_mainRoom);
    		lastRecord.add(t_water);
    	}
	}
    void setConnection() throws SQLException{
    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    	con = DriverManager.getConnection("jdbc:mysql://localhost/sunrise", "sunrise", "777");
    	if(con != null){
    	    System.out.println("Ok!");
    	}
    }
    
    
    public static void main(String[] args) throws SQLException {
		TestJDBC tj = new TestJDBC();
		tj.setConnection();
		tj.getLastRecord();
    }
}
