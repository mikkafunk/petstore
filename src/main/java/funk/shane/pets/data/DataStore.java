package funk.shane.pets.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

public interface DataStore {
    Connection getConn();
    
    public static final class Factory {
        private static DataStore DATASTORE;
        
        private final String url;
        private final String user;
        private final String password;
        
        public Factory(String url, String user, String password) {
            this.url = url;
            this.user = user;
            this.password = password;
        }
        
        public synchronized DataStore create() {
            if(DATASTORE == null) {
                final JdbcConnectionPool cp = JdbcConnectionPool.create(url, user, password);
                
                DATASTORE = new H2DataSource(cp);
            }
            return DATASTORE;
        }
    }
    
    /** 
     * Hardwiring in an H2 datasource for this initial build out
     * TODO: make this database agnostic with a generic typing mechanism 
     * something like:
     * 
     * <pre>
     * class DataSource<T> {
     * T databaseSystem;
     * ...
     * ???
     * </pre>
     * where T could be a database's JDBC connection pool object like JdbcConnectionPool
     */
    final class H2DataSource implements DataStore{
        private final DataSource source;
        
        public H2DataSource(DataSource source) {
            super();
            this.source = source;
        }

        @Override
        public Connection getConn() {
            try {
                return source.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
