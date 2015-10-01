package services.model;

public class ElectricityAccount extends Account {
	private float tariff2;
	
    public ElectricityAccount(int account, float tariff,
	    int currentTestimonies, int preveriousTestimonies,float tariff2) {
	super(account, tariff, currentTestimonies, preveriousTestimonies);

		this.tariff2 = tariff2;
    }

    @Override
    public void setSum() {
    	sum = differenceInTestimonies*tariff;
    	if(differenceInTestimonies <= 100)
    		sum = differenceInTestimonies * tariff;
    	else{
    		sum = (differenceInTestimonies - 100) * tariff2 + 100 * tariff;
    	}
    }
}
