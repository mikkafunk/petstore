package funk.shane.pets;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

public class PetsConfiguration extends Configuration{
    @NotNull
    @Valid
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();
    
    public DatabaseConfiguration getDatabaseConfig() {
        return database;
    }
}
