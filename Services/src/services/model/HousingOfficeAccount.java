package services.model;

public class HousingOfficeAccount {
    int account;
    float tariff;
    float sum;
    
    public HousingOfficeAccount(int account, float tariff) {
	this.account = account;
	this.tariff = tariff;
    }
    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }
    public float getTariff() {
        return tariff;
    }
    public void setTariff(float tariff) {
        this.tariff = tariff;
    }
    public float getSum() {
	setSum();
        return sum;
    }
    public void setSum() {
        sum = tariff;
    }
}
