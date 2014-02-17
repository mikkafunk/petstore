package funk.shane.pets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.yammer.dropwizard.db.DatabaseConfiguration;

import funk.shane.pets.PetsConfiguration;

public class PetsConfigurationTest {
    public PetsConfiguration petsConfig = null;

    @Before
    public void setup() {
        petsConfig = mock(PetsConfiguration.class);
    }
    
    @Test
    public void testGetDatabaseConfiguration() {
        DatabaseConfiguration dataConfig = mock(DatabaseConfiguration.class);
        when(dataConfig.getUrl()).thenReturn("jdbc:mock");
        when(dataConfig.getUser()).thenReturn("user");
        when(dataConfig.getPassword()).thenReturn("password");
        
        when(petsConfig.getDatabaseConfig()).thenReturn(dataConfig);
        
        DatabaseConfiguration testConfig = petsConfig.getDatabaseConfig();
        assertNotNull(testConfig.getUrl());
        assertEquals(dataConfig.getUrl(), testConfig.getUrl());
    }
}
