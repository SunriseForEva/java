package ua.gnatyuk.yaroslav.classes;

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
	private TemperatureInTheHouse firstRec;

	private List<TemperatureInTheHouse> currentHourData = new ArrayList<TemperatureInTheHouse>(60);
	private List<TemperatureInTheHouse> currentDayData = new ArrayList<TemperatureInTheHouse>(1440);
	private List<TemperatureInTheHouse> day = new ArrayList<>();

	private Date currentDay;

	public ReadDataFromSQL() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public void setFirstAndLastRec() {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		lastRec = (TemperatureInTheHouse) session.createQuery("from TemperatureInTheHouse order by id DESC").setMaxResults(1).list().get(0);
		firstRec = (TemperatureInTheHouse) session.createQuery("from TemperatureInTheHouse order by id ASC").setMaxResults(1).list().get(0);

		tx.commit();
		session.clear();
		session.close();

	}

	public void setCurentHourAndDate(Date searchDay) {

		Date first = new Date(searchDay.getTime());
		first.setHours(0);
		first.setMinutes(0);
		first.setSeconds(0);

		Date second = new Date(searchDay.getTime());
		second.setHours(23);
		second.setMinutes(59);
		second.setSeconds(59);

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentDayData.clear();

		currentDayData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		avarage(currentDayData);

		session.clear();

		first = new Date(searchDay.getTime());
		first.setHours(first.getHours() - 1);

		second = new Date(searchDay.getTime());

		currentHourData.clear();
		currentHourData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		session.clear();

	}

	public void setPrevDay(Date searchDay) {
		if (searchDay.compareTo(firstRec.getCurrentDate()) < 0) {
			System.out.println("It's first record in the database.");
			return;
		}

		Date first = new Date(searchDay.getTime());
		first.setHours(0);
		first.setMinutes(0);
		first.setSeconds(0);

		Date second = new Date(searchDay.getTime());
		second.setHours(23);
		second.setMinutes(59);
		second.setSeconds(59);

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentDayData.clear();
		currentDayData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		avarage(currentDayData);

		currentDay.setDate(currentDay.getDate() - 1);

		tx.commit();
		session.close();
	}

	public void setNextDay(Date searchDay) {
		if (searchDay.compareTo(lastRec.getCurrentDate()) > 0) {
			System.out.println("It's last record in a database");
			return;
		}

		Date first = new Date(searchDay.getTime());
		first.setHours(0);
		first.setMinutes(0);
		first.setSeconds(0);

		Date second = new Date(searchDay.getTime());
		second.setHours(23);
		second.setMinutes(59);
		second.setSeconds(59);

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentDayData.clear();
		currentDayData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		Date copyOfLastRec = new Date(lastRec.getCurrentDate().getTime());
		copyOfLastRec.setMinutes(0);
		copyOfLastRec.setSeconds(0);

		avarage(currentDayData);

		if (searchDay.compareTo(copyOfLastRec) < 0) {
			currentDay.setDate(currentDay.getDate() + 1);
		}

		tx.commit();
		session.close();
	}

	public void setPrevHour(Date searchDay) {
		if (searchDay.compareTo(firstRec.getCurrentDate()) < 0) {
			System.out.println("It's first record in the database.");
			return;
		}

		Date first = new Date(searchDay.getTime());
		first.setHours(first.getHours() - 1);

		Date second = new Date(searchDay.getTime());

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentHourData.clear();
		currentHourData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		currentDay.setHours(currentDay.getHours() - 1);
	}

	public void setNextHour(Date searchDay) {
		if (searchDay.compareTo(lastRec.getCurrentDate()) > 0) {
			System.out.println("It's a last record in database.");
			return;
		}

		Date first = new Date(searchDay.getTime());

		Date second = new Date(searchDay.getTime());
		second.setHours(second.getHours() + 1);

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		currentHourData.clear();
		currentHourData = session.createQuery("from TemperatureInTheHouse where currentDate between :stDate and :edDate ").setTimestamp("stDate", first).setTimestamp("edDate", second).list();

		currentDay.setHours(currentDay.getHours() + 1);

	}

	public void setCurrentDay() {
		currentDay = new Date(lastRec.getCurrentDate().getTime());
		currentDay.setMinutes(0);
		currentDay.setSeconds(0);
	}

	public Date getCurrentDate() {
		return currentDay;
	}

	public void avarage(final List<TemperatureInTheHouse> allDay) {
		day.clear();

		int countRec = 0;

		for (int i = 0; i < 24; i++) {
			day.add(new TemperatureInTheHouse());
			for (int j = 0; j < allDay.size(); j++) {
				if (i == allDay.get(j).getCurrentDate().getHours()) {
					day.get(i).setBadRoom(day.get(i).getBadRoom() + allDay.get(j).getBadRoom());
					day.get(i).setBalcony_1(day.get(i).getBalcony_1() + allDay.get(j).getBalcony_1());
					day.get(i).setBalcony_2(day.get(i).getBalcony_2() + allDay.get(j).getBalcony_2());
					day.get(i).setChildRoom(day.get(i).getChildRoom() + allDay.get(j).getChildRoom());
					day.get(i).setHall(day.get(i).getHall() + allDay.get(j).getHall());
					day.get(i).setHallWay(day.get(i).getHallWay() + allDay.get(j).getHallWay());
					day.get(i).setKitchen(day.get(i).getKitchen() + allDay.get(j).getKitchen());
					day.get(i).setOuterForest(day.get(i).getOuterForest() + allDay.get(j).getOuterForest());
					day.get(i).setOuterYard(day.get(i).getOuterYard() + allDay.get(j).getOuterYard());
					day.get(i).setPantry(day.get(i).getPantry() + allDay.get(j).getPantry());

					countRec++;
				}
			}

			day.get(i).setBadRoom(day.get(i).getBadRoom() / countRec);
			day.get(i).setBalcony_1(day.get(i).getBalcony_1() / countRec);
			day.get(i).setBalcony_2(day.get(i).getBalcony_2() / countRec);
			day.get(i).setChildRoom(day.get(i).getChildRoom() / countRec);
			day.get(i).setHall(day.get(i).getHall() / countRec);
			day.get(i).setHallWay(day.get(i).getHallWay() / countRec);
			day.get(i).setKitchen(day.get(i).getKitchen() / countRec);
			day.get(i).setOuterForest(day.get(i).getOuterForest() / countRec);
			day.get(i).setOuterYard(day.get(i).getOuterYard() / countRec);
			day.get(i).setPantry(day.get(i).getPantry() / countRec);

			day.get(i).setCurrentDate(allDay.get(0).getCurrentDate());

			countRec = 0;
		}

		for (int i = 0; i < day.size(); i++) {
			day.get(i).getCurrentDate().setHours(i);
		}
	}

	public List<TemperatureInTheHouse> getCurrentHourData() {
		return currentHourData;
	}

	public List<TemperatureInTheHouse> getCurrentDayData() {
		return day;
	}

	public TemperatureInTheHouse getLastRec() {
		return lastRec;
	}

}
