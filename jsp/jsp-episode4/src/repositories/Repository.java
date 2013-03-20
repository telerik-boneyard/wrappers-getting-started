package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// the repository base class extracts out objects needed by all repositories to stay DRY
public class Repository {

	// sql connection object
	protected Connection conn = null;
	
	public Repository(String path) {

		// initialize the database driver
        try {
        	Class.forName("org.sqlite.JDBC");
        	
			// set the connection instance
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
