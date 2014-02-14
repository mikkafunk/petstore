package funk.shane.pets.health;

import java.sql.Connection;
import java.sql.SQLException;

import com.yammer.metrics.core.HealthCheck;

import funk.shane.pets.data.DataStore;

public class DatabaseHealthCheck extends HealthCheck {

    private final DataStore dataStore;
    
    public DatabaseHealthCheck(final DataStore dataStore) {
        super("database");
        this.dataStore = dataStore;
    }

    @Override
    protected Result check() throws Exception {
        Connection conn = null;
        
        try {
            conn = dataStore.getConn();
            if(conn.isValid(1)) {
                return Result.healthy();
            }
            else {
                return Result.unhealthy("Cannot connect to H2 database");
            }
        }
        finally {
            try {
                if(!conn.isClosed()) {
                    conn.close();
                }
            }
            catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
