package test.JDBC;

import java.util.ArrayList;
import java.util.Date;

public class Temperature {
	Date currentDate;
    double hall;
    double childRoom;
    double kitchen;
    double badRoom;
    double hallWay;
    double pantry;                  //кладовая
    double balcony_1;
    double balcony_2;

    double outerForest;
    double outerYard;

    double water;

    public Temperature(Date currentDate, double hall, double childRoom, double kitchen, double badRoom, double hallWay, double pantry, double balcony_1, double balcony_2, double outerForest, double outerYard, double water) {
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
    /*ArrayList temperature состоит из температур 
	 * 0 - балкон1
	 * 1 - спальня
	 * 2 - Зал
	 * 3 - Балкон2
	 * 4 - детская
	 * 5 - Холл(коридор)
	 * 6 - кухня
	 * 7 - двор
	 * 8 - лес
	 * 9 - вода
	 * 10 - кладовая
	 * 11 - текущее время и дата
	 */
    public Temperature(ArrayList<Object> t){
    	this.balcony_1 =(float) t.get(0);
    	this.badRoom = (float)t.get(1);
    	this.hall =(float) t.get(2);
    	this.balcony_2 = (float)t.get(3);
    	this.childRoom =(float) t.get(4);
    	this.hallWay =(float) t.get(5);
    	this.kitchen =(float) t.get(6);
    	this.outerYard = (float)t.get(7);
    	this.outerForest = (float)t.get(8);
    	this.water = (float)t.get(9);
    	this.pantry = (float)t.get(10);
    	this.currentDate =(Date) t.get(11);
    }

    public Temperature() {
        this.currentDate = null;
        hall = 0;
        childRoom = 0;
        kitchen = 0;
        badRoom = 0;
        hallWay = 0;
        pantry = 0;                  //кладовая
        balcony_1 = 0;
        balcony_2 = 0;

        outerForest = 0;
        outerYard = 0;

        water = 0;
    }
    
    @Override
	public String toString() {
		return "Temperature [currentDate=" + currentDate + ", hall=" + hall
				+ ", childRoom=" + childRoom + ", kitchen=" + kitchen
				+ ", badRoom=" + badRoom + ", hallWay=" + hallWay + ", pantry="
				+ pantry + ", balcony_1=" + balcony_1 + ", balcony_2="
				+ balcony_2 + ", outerForest=" + outerForest + ", outerYard="
				+ outerYard + ", water=" + water + "]";
	}
	public void setAllTemperature( ArrayList<Double> temperature ){
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

    public Date getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public double getHall() {
        return hall;
    }

    public void setHall(double hall) {
        this.hall = hall;
    }

    public double getChildRoom() {
        return childRoom;
    }

    public void setChildRoom(double childRoom) {
        this.childRoom = childRoom;
    }

    public double getKitchen() {
        return kitchen;
    }

    public void setKitchen(double kitchen) {
        this.kitchen = kitchen;
    }

    public double getBadRoom() {
        return badRoom;
    }

    public void setBadRoom(double badRoom) {
        this.badRoom = badRoom;
    }

    public double getHallWay() {
        return hallWay;
    }

    public void setHallWay(double hallWay) {
        this.hallWay = hallWay;
    }

    public double getPantry() {
        return pantry;
    }

    public void setPantry(double pantry) {
        this.pantry = pantry;
    }

    public double getBalcony_1() {
        return balcony_1;
    }

    public void setBalcony_1(double balcony_1) {
        this.balcony_1 = balcony_1;
    }

    public double getBalcony_2() {
        return balcony_2;
    }

    public void setBalcony_2(double balcony_2) {
        this.balcony_2 = balcony_2;
    }

    public double getOuterForest() {
        return outerForest;
    }

    public void setOuterForest(double outerForest) {
        this.outerForest = outerForest;
    }

    public double getOuterYard() {
        return outerYard;
    }

    public void setOuterYard(double outerYard) {
        this.outerYard = outerYard;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }
    
    @SuppressWarnings("deprecation")
    public boolean isHoursEqals(int hour){
	if(this.getCurrentDate().getHours() == hour){
	    return true;
	}
	else{
	    return false;
	}
    }
    
    @SuppressWarnings("deprecation")
	public boolean isDayEquals(int day){
    	if(this.getCurrentDate().getDate() == day){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
