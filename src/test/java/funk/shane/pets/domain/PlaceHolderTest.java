package funk.shane.pets.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaceHolderTest {
    private PlaceHolder ph;
    
    @Before
    public void setup() {
        ph = new PlaceHolder();
    }
    
    @After
    public void tearDown() {
        ph = null;
    }
    
    @Test
    public void addMeTest() {
        assertEquals(10, ph.addMe(3, 7));
    }
}
