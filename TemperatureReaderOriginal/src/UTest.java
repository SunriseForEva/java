import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import TemperatureInTheHouse.model.TemperatureInTheHouse;


public class UTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    TemperatureInTheHouse temp = new TemperatureInTheHouse();
    
    @Test
    public void test1() {
	temp.setCurrentDate(new Date());
	assertEquals(true, temp.isHoursEqals(15));
    }

}
