package funk.shane.pets.resource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.metrics.annotation.Timed;

import funk.shane.pets.data.DataStore;
import funk.shane.pets.util.DbUtils;
import funk.shane.pets.view.DatabaseView;

@Path("data")
@Produces(MediaType.TEXT_HTML)
public class DataViewResource {
    private final DataStore dataStore;
    
    public DataViewResource(final DataStore dataStore) {
        this.dataStore = dataStore;
    }
    
    @Path("settings")
    @GET
    @Timed
    public DatabaseView getSettings() {
        
        Map<String, String> settingsMap = new LinkedHashMap<>();
        
        try {
            ResultSet rs = dataStore.getConn().createStatement().executeQuery(DbUtils.getDbResource("ViewSettings.sql"));
            while(rs.next()) {
                settingsMap.put(rs.getString("name"), rs.getString("value"));
            }
            
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return new DatabaseView(settingsMap);
    }
}
