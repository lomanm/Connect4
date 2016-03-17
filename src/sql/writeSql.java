package sql;
import java.sql.*;

public class writeSql {

	
	public writeSql(String username, String password, String email){
	    
		  try {
	      // create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver"; 
	      String myUrl = "jdbc:mysql://localhost/claimgames";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
	    
	 
	      // the mysql insert statement
	      String query = " INSERT INTO users (id, username, password, email)"
	        + " values (?, ?, ?, ?)";
	 
	      // create the mysql insert prepared statement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, null);
	      preparedStmt.setString (2, username);
	      preparedStmt.setString (3, password);
	      preparedStmt.setString (4, email);
	 
	      // execute the prepared statement
	      preparedStmt.execute();
	       
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	}
	

