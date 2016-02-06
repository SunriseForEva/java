package ua.yaroslav.gnatyuk.classes;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yarik on 17.06.2015.
 */
public class TemperatureInTheHouse {
	int id;
	Date currentDate;
	float hall;
	float childRoom;
	float kitchen;
	float badRoom;
	float hallWay;
	float pantry; // ��������
	float balcony_1;
	float balcony_2;

	float outerForest;
	float outerYard;

	float water;

	public TemperatureInTheHouse(Date currentDate, float hall, float childRoom, float kitchen, float badRoom, float hallWay, float pantry, float balcony_1, float balcony_2, float outerForest, float outerYard, float water) {
		this.currentDate = currentDate;
		this.hall = hall;
		this.childRoom = childRoom;
		this.kitchen = kitchen;
		this.badRoom = badRoom;
		this.hallWay = hallWay;
		this.pantry = pantry;
		this.balcony_1 = balcony_1;
		this.balcony_2 = balcony_2;
		this.outerForest = outerForest;
		this.outerYard = outerYard;
		this.water = water;
	}

	/*
	 * ArrayList temperature ������� �� ���������� 0 - ������1 1 - ������� 2 -
	 * ��� 3 - ������2 4 - ������� 5 - ����(�������) 6 - ����� 7 - ���� 8 - ���
	 * 9 - ���� 10 - �������� 11 - ������� ����� � ����
	 */
	public TemperatureInTheHouse(ArrayList<Object> t) {
		this.balcony_1 = (Float) t.get(0);
		this.badRoom = (Float) t.get(1);
		this.hall = (Float) t.get(2);
		this.balcony_2 = (Float) t.get(3);
		this.childRoom = (Float) t.get(4);
		this.hallWay = (Float) t.get(5);
		this.kitchen = (Float) t.get(6);
		this.outerYard = (Float) t.get(7);
		this.outerForest = (Float) t.get(8);
		this.water = (Float) t.get(9);
		this.pantry = (Float) t.get(10);
		this.currentDate = (Date) t.get(11);
	}

	public TemperatureInTheHouse() {
		this.currentDate = null;
		hall = 0;
		childRoom = 0;
		kitchen = 0;
		badRoom = 0;
		hallWay = 0;
		pantry = 0; // ��������
		balcony_1 = 0;
		balcony_2 = 0;

		outerForest = 0;
		outerYard = 0;

		water = 0;
	}

	public void setAllTemperature(ArrayList<Float> temperature) {
		this.setBalcony_1(temperature.get(0));
		this.setBadRoom(temperature.get(1));
		this.setHall(temperature.get(2));
		this.setBalcony_2(temperature.get(3));
		this.setChildRoom(temperature.get(4));
		this.setHallWay(temperature.get(5));
		this.setKitchen(temperature.get(6));
		this.setPantry(temperature.get(6));

		this.setOuterYard(temperature.get(7));
		this.setOuterForest(temperature.get(8));
		this.setWater(temperature.get(9));
	}

	public String toString() {
		return "Temperature [currentDate=" + currentDate + ", hall=" + hall + ", childRoom=" + childRoom + ", kitchen=" + kitchen + ", badRoom=" + badRoom + ", hallWay=" + hallWay + ", pantry=" + pantry + ", balcony_1=" + balcony_1 + ", balcony_2=" + balcony_2 + ", outerForest=" + outerForest
				+ ", outerYard=" + outerYard + ", water=" + water + "]";
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public float getHall() {
		return hall;
	}

	public void setHall(float hall) {
		this.hall = hall;
	}

	public float getChildRoom() {
		return childRoom;
	}

	public void setChildRoom(float childRoom) {
		this.childRoom = childRoom;
	}

	public float getKitchen() {
		return kitchen;
	}

	public void setKitchen(float kitchen) {
		this.kitchen = kitchen;
	}

	public float getBadRoom() {
		return badRoom;
	}

	public void setBadRoom(float badRoom) {
		this.badRoom = badRoom;
	}

	public float getHallWay() {
		return hallWay;
	}

	public void setHallWay(float hallWay) {
		this.hallWay = hallWay;
	}

	public float getPantry() {
		return pantry;
	}

	public void setPantry(float pantry) {
		this.pantry = pantry;
	}

	public float getBalcony_1() {
		return balcony_1;
	}

	public void setBalcony_1(float balcony_1) {
		this.balcony_1 = balcony_1;
	}

	public float getBalcony_2() {
		return balcony_2;
	}

	public void setBalcony_2(float balcony_2) {
		this.balcony_2 = balcony_2;
	}

	public float getOuterForest() {
		return outerForest;
	}

	public void setOuterForest(float outerForest) {
		this.outerForest = outerForest;
	}

	public float getOuterYard() {
		return outerYard;
	}

	public void setOuterYard(float outerYard) {
		this.outerYard = outerYard;
	}

	public float getWater() {
		return water;
	}

	public void setWater(float water) {
		this.water = water;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
