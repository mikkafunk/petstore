package funk.shane.pets.domain;

import static org.junit.Assert.assertEquals;

import org.joda.time.Instant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PetTest {

    private Pet pet;
    private Instant birthdate = Instant.parse("2014-01-01T10:36:18-06:00");
    
    @Before
    public void setup() throws Exception {
        pet = new Pet("Cat", "Manx", 10.0, birthdate);
    }
    
    @After
    public void tearDown() throws Exception {
        pet = null;
    }
    
    @Test
    public void testPet_type() {
        assertEquals("Cat", pet.getType());
    }
    
    @Test
    public void testPet_breed() {
        assertEquals("Manx", pet.getBreed());
    }
    
    @Test
    public void testPet_Weight() {
        assertEquals(10.0, pet.getWeight(), 0.0);
    }
    
    @Test
    public void testPet_birthdate() {
        assertEquals(birthdate, pet.getBirthDate());
    }
    
    @Test
    public void testPet_equals() {
        Pet testPet = new Pet("Cat", "Manx", 10.0, birthdate);
        assertEquals(testPet, pet);
    }

    @Test
    public void testPet_toString() {
        Pet testPet = new Pet("Cat", "Manx", 10.0, birthdate);
        assertEquals(testPet.toString(), pet.toString());
    }
}
