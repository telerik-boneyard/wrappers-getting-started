package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryBase {

	Connection connection = null;
	
	public RepositoryBase() { }
	
	public RepositoryBase(String path) {

		// initialize the database driver
        try {
        	Class.forName("org.sqlite.JDBC");
        	
			// set the connection instance
			connection = DriverManager.getConnection("jdbc:sqlite:" + path);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
