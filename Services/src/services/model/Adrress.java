package services.model;

public class Adrress{
    String city;
    String street;
    String numberOfTheHouse;
    String numberOfTheFlat;
    
    public Adrress(String city, String street, String numberOfTheHouse,
    		String numberOfTheFlat) {
	
	this.city = city;
	this.street = street;
	this.numberOfTheHouse = numberOfTheHouse;
	this.numberOfTheFlat = numberOfTheFlat;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumberOfTheHouse() {
        return numberOfTheHouse;
    }
    public void setNumberOfTheHouse(String numberOfTheHouse) {
        this.numberOfTheHouse = numberOfTheHouse;
    }
    public String getNumberOfTheFlat() {
        return numberOfTheFlat;
    }
    public void setNumberOfTheFlat(String numberOfTheFlat) {
        this.numberOfTheFlat = numberOfTheFlat;
    }
}