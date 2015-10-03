package test.JDBC;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

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

	Map<Integer,Object> map = new TreeMap<Integer, Object>();
	
	
    public static Connection con = null;
    public static Statement stmt = null;
    public static String request = "SELECT *FROM housestemperatures "+
    "WHERE currentTime>'2015-10-2 12:00:01'&&"+
    		"currentTime<'2015-10-2 13:00:01'";
    
    String requestLastRecord = "SELECT *FROM housestemperatures "+
    "ORDER BY id DESC LIMIT 1";
    
    void getLastRecord() throws SQLException{
    	
    	
    	stmt = con.createStatement();
    	ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
    	getDataFromRequest(rsLastRec,map);
    	
    }
	public void getDataFromRequest(ResultSet rs, Map<Integer, Object> map) throws SQLException { //к аргументам функции добавить карту(map)
		for(int i = 0 ; rs.next() ; i++){
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
    		
    		map.put(i, id);   // вместо ArrayList использовать TreeMap
    		map.put(i,date);
    		map.put(i,t_balconyEast);
    		map.put(i,t_bedroom);
    		map.put(i,t_hall);
    		map.put(i,t_balconyWest);
    		map.put(i,t_childrenroom);
    		map.put(i,t_kitchen);
    		map.put(i,t_pantry);
    		map.put(i,t_outerForest);
    		map.put(i,t_outerYard);
    		map.put(i,t_mainRoom);
    		map.put(i,t_water);
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
