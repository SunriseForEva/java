package ua.gnatyuk.yaroslav.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import ua.gnatyuk.yaroslav.classes.CreateConnectionDB;
import ua.gnatyuk.yaroslav.classes.TemperatureInTheHouse;
import ua.gnatyuk.yaroslav.classes.User;

@Controller
public class Controllers {
	CreateConnectionDB connectionDB;
	TemperatureInTheHouse lastData;

	private static final Logger logger = LoggerFactory.getLogger(Controllers.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/main-window", method = RequestMethod.POST)
	public ModelAndView checkUser(@ModelAttribute("user") User user) {

		try {
			connectionDB = CreateConnectionDB.getInstance(user.getName(), user.getPassword());
			connectionDB.setLastRecord();
			lastData = connectionDB.getLastRecord();

			connectionDB.start();

			return new ModelAndView("main", "user", user).addObject("lastData", lastData).addObject("date", lastData.getCurrentDate());
		} catch (SQLException e) {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "/main-window", method = RequestMethod.GET)
	public ModelAndView mainWindow(@ModelAttribute("user") User user) {
		return new ModelAndView("main", "user", user).addObject("lastData", lastData).addObject("date", lastData.getCurrentDate());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView exit(@ModelAttribute("user") User user) {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/chart-inside", method = RequestMethod.GET)
	public ModelAndView chartInside(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();

		ModelAndView inside = new ModelAndView("chart-inside");
		inside.addObject("currentHour", connectionDB.getCurrentHour());
		inside.addObject("currentDate", connectionDB.getCurrentDay());
		inside.addObject("day", day);
		inside.addObject("hour", hour);

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-prev-hour", method = RequestMethod.GET)
	public ModelAndView chartInsidePrevereiousHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		connectionDB.getPreviousHour(connectionDB.getCurrentHour());
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

		System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

		ModelAndView inside = new ModelAndView("chart-inside");

		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", connectionDB.getCurrentHour());
		inside.addObject("currentDate", connectionDB.getCurrentDay());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-next-hour", method = RequestMethod.GET)
	public ModelAndView chartInsideNextHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		if (!connectionDB.isLastHour(connectionDB.getCurrentHour())) {
			connectionDB.getNextHour(connectionDB.getCurrentHour());
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView inside = new ModelAndView("chart-inside");
			inside.addObject("hour", hour);
			inside.addObject("day", day);
			inside.addObject("currentHour", connectionDB.getCurrentHour());
			inside.addObject("currentDate", connectionDB.getCurrentDay());

			System.gc();

			return inside;
		} else {
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView inside = new ModelAndView("chart-inside");
			inside.addObject("hour", hour);
			inside.addObject("day", day);
			inside.addObject("currentHour", connectionDB.getCurrentHour());
			inside.addObject("currentDate", connectionDB.getCurrentDay());

			System.gc();

			return inside;

		}
	}

	@RequestMapping(value = "/chart-inside-prev-day", method = RequestMethod.GET)
	public ModelAndView chartInsidePrevereiousDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		connectionDB.getPreviousDay(connectionDB.getCurrentDay());
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

		System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

		ModelAndView inside = new ModelAndView("chart-inside");

		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", connectionDB.getCurrentHour());
		inside.addObject("currentDate", connectionDB.getCurrentDay());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-next-day", method = RequestMethod.GET)
	public ModelAndView chartInsideNextDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		if (!connectionDB.isLastDay(connectionDB.getCurrentDay())) {
			connectionDB.getNextDay(connectionDB.getCurrentDay());
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView inside = new ModelAndView("chart-inside");
			inside.addObject("hour", hour);
			inside.addObject("day", day);
			inside.addObject("currentHour", connectionDB.getCurrentHour());
			inside.addObject("currentDate", connectionDB.getCurrentDay());

			System.gc();

			return inside;
		} else {
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView inside = new ModelAndView("chart-inside");
			inside.addObject("hour", hour);
			inside.addObject("day", day);
			inside.addObject("currentHour", connectionDB.getCurrentHour());
			inside.addObject("currentDate", connectionDB.getCurrentDay());

			System.gc();

			return inside;

		}
	}

	@RequestMapping(value = "/chart-outside", method = RequestMethod.GET)
	public ModelAndView chartOutside(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", connectionDB.getCurrentHour());
		outside.addObject("currentDate", connectionDB.getCurrentDay());
		outside.addObject("day", day);
		outside.addObject("hour", hour);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-prev-hour", method = RequestMethod.GET)
	public ModelAndView chartOutsidePrevereiousHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		connectionDB.getPreviousHour(connectionDB.getCurrentHour());
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

		System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", connectionDB.getCurrentHour());
		outside.addObject("currentDate", connectionDB.getCurrentDay());
		outside.addObject("hour", hour);
		outside.addObject("day", day);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-next-hour", method = RequestMethod.GET)
	public ModelAndView chartOutsideNextHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		if (!connectionDB.isLastHour(connectionDB.getCurrentHour())) {
			connectionDB.getNextHour(connectionDB.getCurrentHour());
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();

			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView outside = new ModelAndView("chart-outside");

			outside.addObject("currentHour", connectionDB.getCurrentHour());
			outside.addObject("currentDate", connectionDB.getCurrentDay());
			outside.addObject("hour", hour);
			outside.addObject("day", day);

			System.gc();

			return outside;
		} else {
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView outside = new ModelAndView("chart-outside");

			outside.addObject("currentHour", connectionDB.getCurrentHour());
			outside.addObject("currentDate", connectionDB.getCurrentDay());
			outside.addObject("hour", hour);
			outside.addObject("day", day);

			System.gc();

			return outside;

		}
	}

	@RequestMapping(value = "/chart-outside-prev-day", method = RequestMethod.GET)
	public ModelAndView chartOutsidePrevereiousDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		connectionDB.getPreviousDay(connectionDB.getCurrentDay());
		List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
		List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

		System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", connectionDB.getCurrentHour());
		outside.addObject("currentDate", connectionDB.getCurrentDay());
		outside.addObject("hour", hour);
		outside.addObject("day", day);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-next-day", method = RequestMethod.GET)
	public ModelAndView chartOutsideNextDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		if (!connectionDB.isLastDay(connectionDB.getCurrentDay())) {
			connectionDB.getNextDay(connectionDB.getCurrentDay());
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();

			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView outside = new ModelAndView("chart-outside");

			outside.addObject("currentHour", connectionDB.getCurrentHour());
			outside.addObject("currentDate", connectionDB.getCurrentDay());
			outside.addObject("hour", hour);
			outside.addObject("day", day);

			System.gc();

			return outside;
		} else {
			List<TemperatureInTheHouse> hour = connectionDB.getCurrentHourData();
			List<TemperatureInTheHouse> day = connectionDB.getCurrentDayData();

			System.out.println("hour: " + connectionDB.getCurrentHour() + "date: " + connectionDB.getCurrentDay() + " total records: " + connectionDB.getCurrentHourData().size());

			ModelAndView outside = new ModelAndView("chart-outside");

			outside.addObject("currentHour", connectionDB.getCurrentHour());
			outside.addObject("currentDate", connectionDB.getCurrentDay());
			outside.addObject("hour", hour);
			outside.addObject("day", day);

			System.gc();

			return outside;

		}
	}
}
