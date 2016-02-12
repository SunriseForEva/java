package ua.gnatyuk.yaroslav.controllers;

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

import ua.gnatyuk.yaroslav.classes.ReadDataFromSQL;
import ua.gnatyuk.yaroslav.classes.TemperatureInTheHouse;
import ua.gnatyuk.yaroslav.classes.User;

@Controller
public class Controllers {
	ReadDataFromSQL data;
	TemperatureInTheHouse lastData;

	private static final Logger logger = LoggerFactory.getLogger(Controllers.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/main-window", method = RequestMethod.POST)
	public ModelAndView checkUser(@ModelAttribute("user") User user) {
		data = new ReadDataFromSQL();
		data.setFirstAndLastRec();
		data.setCurrentDay();
		return new ModelAndView("main", "user", user).addObject("lastData", data.getLastRec()).addObject("date", data.getLastRec().getCurrentDate());
	}

	@RequestMapping(value = "/main-window", method = RequestMethod.GET)
	public ModelAndView mainWindow(@ModelAttribute("user") User user) {
		return new ModelAndView("main", "user", user).addObject("lastData", data.getLastRec()).addObject("date", data.getLastRec().getCurrentDate());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView exit(@ModelAttribute("user") User user) {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/chart-inside", method = RequestMethod.GET)
	public ModelAndView chartInside(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		data.setCurentHourAndDate(data.getCurrentDate());

		List<TemperatureInTheHouse> day = data.getCurrentDayData();
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();

		ModelAndView inside = new ModelAndView("chart-inside");
		inside.addObject("currentHour", data.getCurrentDate().getHours());
		inside.addObject("currentDate", data.getCurrentDate());
		inside.addObject("day", day);
		inside.addObject("hour", hour);

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-prev-hour", method = RequestMethod.GET)
	public ModelAndView chartInsidePrevereiousHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setPrevHour(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView inside = new ModelAndView("chart-inside");

		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", data.getCurrentDate().getHours());
		inside.addObject("currentDate", data.getCurrentDate());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-next-hour", method = RequestMethod.GET)
	public ModelAndView chartInsideNextHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setNextHour(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView inside = new ModelAndView("chart-inside");
		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", data.getCurrentDate().getHours());
		inside.addObject("currentDate", data.getCurrentDate());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-prev-day", method = RequestMethod.GET)
	public ModelAndView chartInsidePrevereiousDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setPrevDay(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView inside = new ModelAndView("chart-inside");

		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", data.getCurrentDate().getHours());
		inside.addObject("currentDate", data.getCurrentDate());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-inside-next-day", method = RequestMethod.GET)
	public ModelAndView chartInsideNextDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setNextDay(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView inside = new ModelAndView("chart-inside");
		inside.addObject("hour", hour);
		inside.addObject("day", day);
		inside.addObject("currentHour", data.getCurrentDate().getHours());
		inside.addObject("currentDate", data.getCurrentDate());

		System.gc();

		return inside;
	}

	@RequestMapping(value = "/chart-outside", method = RequestMethod.GET)
	public ModelAndView chartOutside(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setNextHour(data.getCurrentDate());
		data.setNextDay(data.getCurrentDate());

		List<TemperatureInTheHouse> day = data.getCurrentDayData();
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", data.getCurrentDate().getHours());
		outside.addObject("currentDate", data.getCurrentDate());
		outside.addObject("day", day);
		outside.addObject("hour", hour);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-prev-hour", method = RequestMethod.GET)
	public ModelAndView chartOutsidePrevereiousHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setPrevHour(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", data.getCurrentDate().getHours());
		outside.addObject("currentDate", data.getCurrentDate());
		outside.addObject("day", day);
		outside.addObject("hour", hour);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-next-hour", method = RequestMethod.GET)
	public ModelAndView chartOutsideNextHour(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setNextHour(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();

		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", data.getCurrentDate().getHours());
		outside.addObject("currentDate", data.getCurrentDate());
		outside.addObject("day", day);
		outside.addObject("hour", hour);

		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-prev-day", method = RequestMethod.GET)
	public ModelAndView chartOutsidePrevereiousDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setPrevDay(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();
		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", data.getCurrentDate().getHours());
		outside.addObject("currentDate", data.getCurrentDate());
		outside.addObject("day", day);
		outside.addObject("hour", hour);
		System.gc();

		return outside;
	}

	@RequestMapping(value = "/chart-outside-next-day", method = RequestMethod.GET)
	public ModelAndView chartOutsideNextDay(HttpSession session) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		data.setNextDay(data.getCurrentDate());
		List<TemperatureInTheHouse> hour = data.getCurrentHourData();

		List<TemperatureInTheHouse> day = data.getCurrentDayData();

		ModelAndView outside = new ModelAndView("chart-outside");

		outside.addObject("currentHour", data.getCurrentDate().getHours());
		outside.addObject("currentDate", data.getCurrentDate());
		outside.addObject("day", day);
		outside.addObject("hour", hour);

		System.gc();

		return outside;
	}
}
