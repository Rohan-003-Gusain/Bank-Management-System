package bankmanagementsystem.db;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conn {
	
	private static Properties props = new Properties();
    
	static {
		try {
			InputStream in = Conn.class
					.getClassLoader()
					.getResourceAsStream("bankmanagementsystem/db.properties");
			
			if (in == null) {
				throw new RuntimeException("db.properties file not found in resources");
			}
			
			props.load(in);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize database connection");
		}
	}
	
	public Conn() {
		
	}

	public Connection getConnection() {

        try {
        	return DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));
		} catch (Exception ex) {
			throw new RuntimeException("Database connection failed", ex);
		}
    }
			    
}
    
