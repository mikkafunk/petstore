package funk.shane.pets.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.base.Strings;

public class DbUtils {
    
    private final static String SQL_PREFIX = "funk/shane/pets/sql/";
    private final static String FILES_PREFIX = "funk/shane/pets/files/";
//    private final static String NEWLINE = System.getProperty("line.separator");
    private final static String ONE_SPACE = " ";
    
    public DbUtils() {   }
    
    
    public static String getDbResource(String source) throws IOException {
        if(Strings.isNullOrEmpty(source)) {
            throw new RuntimeException("Cannot pass in null/empty string as DB Resource");
        }
        
        String resource = null;
        final StringBuilder sb = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(
            DbUtils.class.getClassLoader().getResourceAsStream(selectLocationPrefix(source))));
        while( (resource = br.readLine()) != null ) {
            /* a little file scrubbing might be useful to remove potential spacing issues */
            sb.append(resource.trim()).append(ONE_SPACE);
        }
        br.close();
        
        return sb.toString();
    }
    
    /**
     * Utility method to allow for quasi-dynamic selection of resource location
     * @param source
     * @return String - path prefix location appropiate for filetype
     */
    private static String selectLocationPrefix(String source) {
        String prefix = null;
        
        switch(source.substring(source.lastIndexOf('.'))) {
            case ".sql":
                prefix = SQL_PREFIX;
                break;
            case ".txt":
                prefix = FILES_PREFIX;
                break;
            default:
                prefix = "";
        }
        
        return prefix + source;
    }
}
