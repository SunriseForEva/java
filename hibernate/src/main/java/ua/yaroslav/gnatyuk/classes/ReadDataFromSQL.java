package ua.yaroslav.gnatyuk.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ReadDataFromSQL {
	private static SessionFactory factory;

	private TemperatureInTheHouse lastRec;
	private List<TemperatureInTheHouse> currentHourData;
	private List<TemperatureInTheHouse> currentDayData = new ArrayList<TemperatureInTheHouse>(1440);

	private Date currentDay;

	public ReadDataFromSQL() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {

		ReadDataFromSQL rdfs = new ReadDataFromSQL();
		rdfs.getLastRec();
		rdfs.setCurrentDay();
		rdfs.setPreveriousHour(rdfs.getCurrentDate());
	}

	public void getLastRec() {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		lastRec = (TemperatureInTheHouse) session.createQuery("from TemperatureInTheHouse order by id DESC").setMaxResults(1).list().get(0);

		tx.commit();
		session.clear();
		session.close();

	}

	public void setPreveriousHour(Date currentDay) {
		Date first = new Date(currentDay.getTime());
		first.setHours(first.getHours() - 1);
		Date second = new Date(currentDay.getTime());

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentHourData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		for (TemperatureInTheHouse temperatureInTheHouse : currentHourData) {
			System.out.println(temperatureInTheHouse.toString());
		}

		tx.commit();
		session.clear();
		session.close();
	}

	public void setCurrentDay() {
		currentDay = new Date(lastRec.getCurrentDate().getTime());
		currentDay.setMinutes(0);
	}

	public Date getCurrentDate() {
		return currentDay;
	}
}
