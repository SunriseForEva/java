package services.model;
import java.util.*;

public class Services {
    Adrress adrress;
    Date dateOfTestimonies;
    ElectricityAccount electricity;
    WaterAccaunt water;
    GasAccaunt gas;
    HousingOfficeAccount housingOffice;
    float totalSum;

    public Services() {
    }
    public Services(Adrress adrress, Date dateOfTestTestimonies, ElectricityAccount electricity, WaterAccaunt water,
	    GasAccaunt gas, HousingOfficeAccount housingOffice){
	
	this.adrress = adrress;
	this.dateOfTestimonies = dateOfTestTestimonies;
	this.electricity = electricity;
	this.water = water;
	this.gas = gas;
	this.housingOffice = housingOffice;
    }
    
    public HousingOfficeAccount getHousingOffice() {
        return housingOffice;
    }

    public void setHousingOffice(int account, float tariff) {
        this.housingOffice = new HousingOfficeAccount(account, tariff);
    }
    
    public Adrress getAdrress() {
        return adrress;
    }

    public ElectricityAccount getElectricity() {
        return electricity;
    }

    public WaterAccaunt getWater() {
        return water;
    }

    public GasAccaunt getGas() {
        return gas;
    }

    public float getTotalSum() {
	
        return totalSum;
    }

    public void setTotalSum() {
	totalSum = water.getSum() + gas.getSum() + electricity.getSum() + housingOffice.getSum();
    }
}

abstract class Account{
    int account;
    float tariff;
    int currentTestimonies;
    int preveriousTestimonies;
    int differenceInTestimonies;
    float sum;
    
    public Account(int account, float tariff, int currentTestimonies,
	    int preveriousTestimonies) {
	
	this.account = account;
	this.tariff = tariff;
	this.currentTestimonies = currentTestimonies;
	this.preveriousTestimonies = preveriousTestimonies;
	this.differenceInTestimonies = getDifferenceInTestimonies();
    }
    
    public Account(int account, float tariff){
    	this.tariff = tariff;
    	this.sum = tariff; 
    }

    public float getTariff() {
        return tariff;
    }

    public void setTariff(float tariff) {
        this.tariff = tariff;
    }
    
    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getCurrentTestimonies() {
        return currentTestimonies;
    }

    public void setCurrentTestimonies(int currentTestimonies) {
        this.currentTestimonies = currentTestimonies;
    }

    public int getPreveriousTestimonies() {
        return preveriousTestimonies;
    }

    public void setPreveriousTestimonies(int preveriousTestimonies) {
        this.preveriousTestimonies = preveriousTestimonies;
    }

    public int getDifferenceInTestimonies() {
        return differenceInTestimonies = currentTestimonies - preveriousTestimonies;
    }

    public void setDifferenceInTestimonies(int differenceInTestimonies) {
        this.differenceInTestimonies = differenceInTestimonies;
    }

    abstract public void setSum(); 
   
    public float getSum(){
	setSum();
	return sum;
    }
}
