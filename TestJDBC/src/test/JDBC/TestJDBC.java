package test.JDBC;
import java.sql.*;
public class TestJDBC {
    public static Connection con = null;
    public static Statement stmt = null;
    public static String request = "INSERT INTO PERSON" + 
                                   "(NAME,SURNAME)"+
                                   "VALUES('1111','2222')";
    
    public static void main(String[] args) throws SQLException {
	// TODO Auto-generated method stub
	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	con = DriverManager.getConnection("jdbc:mysql://localhost/sunrise", "sunrise", "777");
	if(con != null){
	    System.out.println("Ok!");
	}
	
	stmt = con.createStatement();
	stmt.execute(request);
    }
}
