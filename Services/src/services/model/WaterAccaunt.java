package services.model;

public class WaterAccaunt extends Account {

    public WaterAccaunt(int account, float tariff, int currentTestimonies,
	    int preveriousTestimonies) {
	super(account, tariff, currentTestimonies, preveriousTestimonies);
	// TODO Auto-generated constructor stub
    }
    public WaterAccaunt(int account, float tariff) {
    	super(account, tariff);
	}
    @Override
    public void setSum() {
	if(differenceInTestimonies == 0){
	    sum = tariff;
	}else
	{
	    sum = differenceInTestimonies*tariff;
	}
    }

}
