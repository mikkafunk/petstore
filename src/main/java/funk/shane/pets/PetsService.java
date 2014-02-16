package funk.shane.pets;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.views.ViewBundle;

import funk.shane.pets.data.DataStore;
import funk.shane.pets.health.DatabaseHealthCheck;
import funk.shane.pets.resource.DataViewResource;
import funk.shane.pets.resource.IndexResource;

public class PetsService extends Service<PetsConfiguration> {

    public static void main(String[] args) throws Exception{
        new PetsService().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<PetsConfiguration> bootstrap) {
        bootstrap.setName("Pets");
        
        bootstrap.getObjectMapperFactory().registerModule(new JodaModule());
        bootstrap.getObjectMapperFactory().setDateFormat(new ISO8601DateFormat());
        
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets", "/assets/"));
    }

    @Override
    public void run(PetsConfiguration configuration, Environment environment) throws Exception {
        DatabaseConfiguration dbConfig = configuration.getDatabaseConfig();
        final DataStore.Factory datastoreFactory = new DataStore.Factory(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
        final DataStore dataStore = datastoreFactory.create();
        
        environment.addHealthCheck(new DatabaseHealthCheck(dataStore));
        environment.addResource(new DataViewResource(dataStore));
        environment.addResource(new IndexResource());
    }
}
