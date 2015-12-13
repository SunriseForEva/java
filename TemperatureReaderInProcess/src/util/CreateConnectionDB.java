package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import TemperatureInTheHouse.model.TemperatureInTheHouse;

public class CreateConnectionDB {

	private final static CreateConnectionDB connectDB = null;

	private String URL = "jdbc:mysql://178.219.93.93:3306/myDb";
	private String user = "sunrise4eva";
	private String password = "sunrisesql1982";

	private Connection con = null;
	private Statement stmt = null;

	Date currentDay = null;

	private String requestSearchDate = "SELECT *FROM housestemperatures "
			+ "WHERE DATE(currentTime)=";
	private String requestLastRecord = "SELECT *FROM housestemperatures "
			+ "ORDER BY id DESC LIMIT 1";

	private ArrayList<TemperatureInTheHouse> lastRecord = new ArrayList<TemperatureInTheHouse>();

	private ArrayList<TemperatureInTheHouse> currentHourData = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> currentDayData = new ArrayList<TemperatureInTheHouse>();

	private ArrayList<TemperatureInTheHouse> searchHourData = new ArrayList<TemperatureInTheHouse>();
	private ArrayList<TemperatureInTheHouse> searchDayData = new ArrayList<TemperatureInTheHouse>();

	/* Set connection to MySql server */

	private CreateConnectionDB() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(URL, user, password);
		if (con != null) {
			System.out.println("Connection setup succesful with DB");
		}
		stmt = con.createStatement();
	}

	public static CreateConnectionDB getInstance() throws SQLException {
		if (connectDB == null) {
			return new CreateConnectionDB();
		} else {
			return connectDB;
		}

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

	public void setCurrentDayData() throws SQLException {
		ResultSet rsRecPerDay = stmt.executeQuery(requestSearchDate + "'"
				+ getStingFromDate(currentDay) + "'");
		getDataFromRequest(rsRecPerDay, currentDayData);
	}

	public void setSearchDayData(Date searchingDate) throws SQLException {
		searchDayData = new ArrayList<TemperatureInTheHouse>();
		ResultSet rsRecPerDay = stmt.executeQuery(requestSearchDate + "'"
				+ getStingFromDate(searchingDate) + "'");
		getDataFromRequest(rsRecPerDay, searchDayData);
	}

	/* Last record take from table and sets current(date of last record) */
	public void setLastRecord() throws SQLException {
		ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
		getDataFromRequest(rsLastRec, lastRecord);
		currentDay = lastRecord.get(0).getCurrentDate();
	}

	/* All data read from request and put into object "Temperature" */
	private void getDataFromRequest(ResultSet rs,
			ArrayList<TemperatureInTheHouse> listTemperature)
			throws SQLException {
		while (rs.next()) {
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

	/* Take Date from String */
	private static Date getDateFromString(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date temp = new Date();
		try {
			temp = sdf.parse(d);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return temp;
	}

	private static String getStingForDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	private static String getStingFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	@SuppressWarnings("deprecation")
	public void setCurrentHourData(Date currentDay) {
		Date prevDate = new Date(currentDay.getTime());
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		System.out.println(getStingForDateTime(prevDate));

		Date nextDate = new Date(currentDay.getTime());
		nextDate.setMinutes(59);
		nextDate.setSeconds(59);

		System.out.println(getStingForDateTime(nextDate));

		String getCurrentHour = "SELECT *FROM housestemperatures "
				+ "WHERE currentTime<'" + getStingForDateTime(nextDate)
				+ "' && currentTime>'" + getStingForDateTime(prevDate) + "'";

		ResultSet rsLastRec;
		try {
			currentHourData = new ArrayList<TemperatureInTheHouse>();
			rsLastRec = stmt.executeQuery(getCurrentHour);
			getDataFromRequest(rsLastRec, currentHourData);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void showAllRecordLastHour() {
		Iterator<TemperatureInTheHouse> iter = currentHourData.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().toString());
		}
	}
}
