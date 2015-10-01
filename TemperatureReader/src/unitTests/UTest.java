package unitTests;
import static org.junit.Assert.*;


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
    	assertEquals(false, false);
    }
}
