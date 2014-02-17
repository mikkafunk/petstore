package funk.shane.pets;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.json.ObjectMapperFactory;

import funk.shane.pets.health.DatabaseHealthCheck;
import funk.shane.pets.resource.DataViewResource;
import funk.shane.pets.resource.IndexResource;

public class PetsServiceTest {

    @Test
    public void testInitializeBootstrapOfPetsConfiguration() {
        @SuppressWarnings("unchecked")
        Bootstrap<PetsConfiguration> mockBootStrap = mock(Bootstrap.class);
        ObjectMapperFactory objectMapperFactory = spy(new ObjectMapperFactory());
        
        when(mockBootStrap.getObjectMapperFactory()).thenReturn(objectMapperFactory);
        
        new PetsService().initialize(mockBootStrap);
        
        verify(mockBootStrap).setName("pets");
        verify(mockBootStrap, times(2)).getObjectMapperFactory();
        verify(objectMapperFactory).registerModule(isA(JodaModule.class));
        verify(objectMapperFactory).setDateFormat(isA(ISO8601DateFormat.class));
    }

    @Test
    public void testRunPetsConfigurationEnvironment() throws Exception {
        DatabaseConfiguration mockDatabaseConfiguration = mock(DatabaseConfiguration.class);
        when(mockDatabaseConfiguration.getUrl()).thenReturn("jdbc:mockDb");
        when(mockDatabaseConfiguration.getUser()).thenReturn("user");
        when(mockDatabaseConfiguration.getPassword()).thenReturn("password");
        
        PetsConfiguration mockPetsConfiguration = mock(PetsConfiguration.class);
        when(mockPetsConfiguration.getDatabaseConfig()).thenReturn(mockDatabaseConfiguration);
        Environment mockEnv = mock(Environment.class);

        new PetsService().run(mockPetsConfiguration, mockEnv);
        verify(mockEnv).addResource(isA(IndexResource.class));
        verify(mockEnv).addResource(isA(DataViewResource.class));
        
        verify(mockEnv).addHealthCheck(isA(DatabaseHealthCheck.class));
    }
}
