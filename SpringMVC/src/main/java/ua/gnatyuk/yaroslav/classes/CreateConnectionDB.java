package ua.gnatyuk.yaroslav.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateConnectionDB extends Thread {

	boolean connectionIsComplete = false;

	private final static CreateConnectionDB connectDB = null;
	private Connection con = null;
	private Statement stmt = null;

	private String URL = "jdbc:mysql://178.219.93.93:3306/myDb";
	private String requestLastRecord = "SELECT *FROM housestemperatures " + "ORDER BY id DESC LIMIT 1";

	private byte currentHour;
	private Date currentDay = null;

	private List<TemperatureInTheHouse> lastRecord = new ArrayList<TemperatureInTheHouse>();
	private List<TemperatureInTheHouse> currentHourData = new ArrayList<TemperatureInTheHouse>();
	private List<TemperatureInTheHouse> currentDayData = new ArrayList<TemperatureInTheHouse>();
	private List<TemperatureInTheHouse> monthData = new ArrayList<TemperatureInTheHouse>();

	/* Set connection to MySql server(Pattern Singleton) */

	private CreateConnectionDB(String user, String password) throws SQLException {

		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(URL, user, password);
		if (con != null) {
			System.out.println("Connection setup succesful with DB");
			connectionIsComplete = true;
		}
		stmt = con.createStatement();
	}

	public static CreateConnectionDB getInstance(String user, String password) throws SQLException {
		if (user == null || password == null) {

			throw new NullPointerException();
		}

		if (connectDB == null) {
			return new CreateConnectionDB(user, password);
		} else {
			return connectDB;
		}

	}

	// It is all data from 00:00 to 23:59 today
	@SuppressWarnings("deprecation")
	public void setCurrentDayData() {
		for (int i = 0; i < monthData.size(); i += 60) {
			if (monthData.get(i).getCurrentDate().getDate() == currentDay.getDate() && monthData.get(i).getCurrentDate().getMonth() == currentDay.getMonth()) {
				currentDayData.add(monthData.get(i));
			}
		}
		System.out.println("Total records per day: " + currentDayData.size());
	}

	/*
	 * Last record take from table and sets to current(date of last record hours
	 * - 0,minutes - 0,seconds 0 )
	 */
	@SuppressWarnings("deprecation")
	public void setLastRecord() throws SQLException {
		ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
		getDataFromRequest(rsLastRec, lastRecord);
		currentDay = new Date(lastRecord.get(0).getCurrentDate().getTime());
		currentDay.setHours(0);
		currentDay.setMinutes(0);
		currentDay.setSeconds(0);
		currentHour = (byte) lastRecord.get(0).getCurrentDate().getHours();
	}

	public void refreshMain() throws SQLException {
		ResultSet rsLastRec = stmt.executeQuery(requestLastRecord);
		getDataFromRequest(rsLastRec, lastRecord);
	}

	/* All data read from request and put into object "Temperature" */
	private void getDataFromRequest(ResultSet rs, List<TemperatureInTheHouse> lastRecord2) throws SQLException {
		while (rs.next()) {
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
			lastRecord2.add(new TemperatureInTheHouse(record));
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

	@SuppressWarnings("deprecation")
	public void setCurrentHourData(Date currentDay) {

		Date prevDate = new Date(currentDay.getTime());
		prevDate.setHours(currentHour);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date nextDate = new Date(currentDay.getTime());
		nextDate.setHours(currentHour);
		nextDate.setMinutes(59);
		nextDate.setSeconds(59);

		currentHourData = new ArrayList<TemperatureInTheHouse>(60);

		for (int i = 0; i < monthData.size(); i++) {
			if (monthData.get(i).getCurrentDate().compareTo(prevDate) > 0 && monthData.get(i).getCurrentDate().compareTo(nextDate) < 0) {
				currentHourData.add(monthData.get(i));
			}
		}

		if (!isLastHour(getCurrentHour())) {
			for (int i = 0; i < currentHourData.size(); i++) {
				if (currentHourData.get(i).equals(null)) {
					currentHourData.set(i, currentHourData.get(0));
				}
			}
		}

		System.out.println("Total record per las hour" + getCurrentHourData().size());
	}

	// This method must start first
	@SuppressWarnings("deprecation")
	public void setMonthData(Date currentDay) {

		Date prevDate = new Date(currentDay.getTime());
		prevDate.setDate(getLastRecord().getCurrentDate().getDate());
		prevDate.setMonth(prevDate.getMonth() - 1);
		prevDate.setHours(0);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date currDate = new Date(currentDay.getTime());
		currDate.setHours(23);
		currDate.setMinutes(59);
		currDate.setSeconds(59);

		String getCurrentHour = "SELECT *FROM housestemperatures " + "WHERE currentTime<='" + getStingForDateTime(currDate) + "' && currentTime>='" + getStingForDateTime(prevDate) + "'";

		ResultSet rsLastRec;
		try {
			monthData = new ArrayList<TemperatureInTheHouse>();
			rsLastRec = stmt.executeQuery(getCurrentHour);
			getDataFromRequest(rsLastRec, monthData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void getPreviousHour(byte currentHour) {
		Date prevDate = new Date(currentDay.getTime());
		prevDate.setHours(getCurrentHour() - 1);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date currDate = new Date(currentDay.getTime());
		currDate.setHours(getCurrentHour() - 1);
		currDate.setMinutes(59);
		currDate.setSeconds(59);

		currentHourData = new ArrayList<TemperatureInTheHouse>(60);

		for (TemperatureInTheHouse temperatureInTheHouse : monthData) {
			if (prevDate.compareTo(temperatureInTheHouse.getCurrentDate()) == -1 && currDate.compareTo(temperatureInTheHouse.getCurrentDate()) == 1) {
				currentHourData.add(temperatureInTheHouse);
			}

		}

		setCurrentHour(--currentHour);
		if (currentHour == -1) {
			setCurrentHour((byte) 23);
			currentDay.setDate(currentDay.getDate() - 1);
		}

		if (!isLastHour(getCurrentHour())) {
			if ((60 - getCurrentHourData().size()) < 7) {
				for (int i = getCurrentHourData().size(); i < 60; i++) {
					currentHourData.add(getCurrentHourData().get(60 - i));
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void getPreviousDay(Date currentDay) {

		if (currentDay.getDate() == getLastRecord().getCurrentDate().getDate() && currentDay.getMonth() < getLastRecord().getCurrentDate().getMonth()) {
			Date nextMonth = new Date(currentDay.getTime());
			nextMonth.setDate(currentDay.getDate() - 1);

			monthData = new ArrayList<TemperatureInTheHouse>();
			setMonthData(nextMonth);

		}

		Date prevDate = new Date(currentDay.getTime());
		prevDate.setDate(prevDate.getDate() - 1);
		prevDate.setHours(0);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date currDate = new Date(currentDay.getTime());
		currDate.setDate(currDate.getDate() - 1);
		currDate.setHours(23);
		currDate.setMinutes(59);
		currDate.setSeconds(59);

		currentDayData = new ArrayList<TemperatureInTheHouse>(144);

		for (int i = 0; i < monthData.size(); i += 60) {
			if (prevDate.compareTo(monthData.get(i).getCurrentDate()) == -1 && currDate.compareTo(monthData.get(i).getCurrentDate()) == 1) {
				currentDayData.add(monthData.get(i));
			}
		}

		currentDay.setDate(currentDay.getDate() - 1);

		if (!isLastDay(getCurrentDay())) {
			if (getCurrentDayData().size() < 24) {
				for (int i = getCurrentDayData().size(); i < 24; i++) {
					currentDayData.add(getCurrentDayData().get(getCurrentDayData().size() - 1));
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void getNextDay(Date currentDay) {
		Date prevDate = new Date(currentDay.getTime());
		prevDate.setDate(prevDate.getDate() + 1);
		prevDate.setHours(0);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date currDate = new Date(currentDay.getTime());
		currDate.setDate(currDate.getDate() + 1);
		currDate.setHours(23);
		currDate.setMinutes(59);
		currDate.setSeconds(59);

		currentDayData = new ArrayList<TemperatureInTheHouse>(24);

		for (int i = 0; i < monthData.size(); i += 60) {
			if (prevDate.compareTo(monthData.get(i).getCurrentDate()) == -1 && currDate.compareTo(monthData.get(i).getCurrentDate()) == 1) {
				currentDayData.add(monthData.get(i));
			}
		}

		currentDay.setDate(currentDay.getDate() + 1);

		if (!isLastDay(getCurrentDay())) {
			if (getCurrentDayData().size() < 24) {
				for (int i = getCurrentDayData().size(); i < 24; i++) {
					currentDayData.add(getCurrentDayData().get(getCurrentDayData().size() - 1));
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void getNextHour(byte currentHour) {
		Date prevDate = new Date(currentDay.getTime());
		prevDate.setHours(getCurrentHour() + 1);
		prevDate.setMinutes(0);
		prevDate.setSeconds(0);

		Date currDate = new Date(currentDay.getTime());
		currDate.setHours(getCurrentHour() + 1);
		currDate.setMinutes(59);
		currDate.setSeconds(59);

		currentHourData = new ArrayList<TemperatureInTheHouse>(60);

		// currentHourData.get(0).getCurrentDate().get

		for (TemperatureInTheHouse temperatureInTheHouse : monthData) {
			if (prevDate.compareTo(temperatureInTheHouse.getCurrentDate()) == -1 && currDate.compareTo(temperatureInTheHouse.getCurrentDate()) == 1) {
				currentHourData.add(temperatureInTheHouse);
			}

		}

		setCurrentHour(++currentHour);
		if (currentHour == 24) {
			setCurrentHour((byte) 0);
			currentDay.setDate(currentDay.getDate() + 1);
		}

		if (!isLastHour(getCurrentHour())) {
			if ((60 - getCurrentHourData().size()) < 7) {
				for (int i = getCurrentHourData().size(); i < 60; i++) {
					currentHourData.add(getCurrentHourData().get(60 - i));
				}
			}
		}

	}

	public byte getCurrentHour() {
		return currentHour;
	}

	public void setCurrentHour(byte currentHour) {
		this.currentHour = currentHour;
	}

	public List<TemperatureInTheHouse> getCurrentDayData() {
		return currentDayData;
	}

	public List<TemperatureInTheHouse> getCurrentHourData() {
		return currentHourData;
	}

	public Date getCurrentDay() {
		return currentDay;
	}

	public TemperatureInTheHouse getLastRecord() {
		int possition = lastRecord.size() - 1;
		return lastRecord.get(possition);
	}

	public void run() {

		System.out.println("started at " + new Date(System.currentTimeMillis()));
		setMonthData(getCurrentDay());// getCurrentDay() -> This method must
										// start first
		setCurrentDayData();
		setCurrentHourData(getCurrentDay());
		System.out.println("stoped at " + new Date(System.currentTimeMillis()));
	}

	public boolean isConnectionComplete() {
		return connectionIsComplete;
	}

	public void setConnectionIsComplete(boolean connectionIsComplete) {
		this.connectionIsComplete = connectionIsComplete;
	}

	public boolean isLastHour(byte hour) {
		if (getLastRecord().getCurrentDate().getHours() == hour)
			return true;
		else
			return false;
	}

	public boolean isLastDay(Date date) {
		if (getLastRecord().getCurrentDate().getYear() == date.getYear() && getLastRecord().getCurrentDate().getMonth() == date.getMonth() && getLastRecord().getCurrentDate().getDate() == date.getDate())
			return true;
		else
			return false;
	}
}
