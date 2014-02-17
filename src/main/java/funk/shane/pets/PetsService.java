package funk.shane.pets;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.lifecycle.Managed;
import com.yammer.dropwizard.views.ViewBundle;

import funk.shane.pets.data.DataStore;
import funk.shane.pets.health.DatabaseHealthCheck;
import funk.shane.pets.resource.DataViewResource;
import funk.shane.pets.resource.IndexResource;

public class PetsService extends Service<PetsConfiguration> implements Managed{

    public static void main(String[] args) throws Exception{
        new PetsService().run(args);
    }
    
    /**
     * initialize the server resource
     * 
     * Includes:
     *   - ViewBundle()
     *   - AssetsBundle()
     */
    @Override
    public void initialize(Bootstrap<PetsConfiguration> bootstrap) {
        bootstrap.setName("pets");
        
        bootstrap.getObjectMapperFactory().registerModule(new JodaModule());
        bootstrap.getObjectMapperFactory().setDateFormat(new ISO8601DateFormat());
        
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets", "/assets/"));
    }

    /**
     * starts the server
     * 
     * Resources include
     *   - DataViewResource (database test page)
     *   - IndexResource (root page)
     *   
     * HealthCheck
     *   - DatabaseHealthCheck - basic check include 
     */
    @Override
    public void run(PetsConfiguration configuration, Environment environment) throws Exception {
        DatabaseConfiguration dbConfig = configuration.getDatabaseConfig();
        final DataStore.Factory datastoreFactory = new DataStore.Factory(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
        final DataStore dataStore = datastoreFactory.create();
        
        environment.addResource(new DataViewResource(dataStore));
        environment.addResource(new IndexResource());

        environment.addHealthCheck(new DatabaseHealthCheck(dataStore));
    }

    /** 
     * Check that the database is running - if not, start it
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        // still developing the pet store data model
    }

    /**
     * stop the database 
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
    }
}
