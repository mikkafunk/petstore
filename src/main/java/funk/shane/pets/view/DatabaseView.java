package funk.shane.pets.view;

import java.util.Map;

import com.yammer.dropwizard.views.View;

public class DatabaseView extends View {
    
    private Map<String, String> metaMap;

    public DatabaseView( Map<String, String> map) {
        super("dataView.ftl");
        this.metaMap = map;
    }
    
    public Map<String, String> getDataMap() {
        return metaMap;
    }
}
