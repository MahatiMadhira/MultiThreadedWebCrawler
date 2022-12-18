package webCrawler;

import java.sql.*;

import org.sqlite.SQLiteDataSource;
public class Database_URL {
	Connection conn =null;
	Statement stmt = null;
	SQLiteDataSource ds = null;
	
	public void connectCreate() {
		
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	conn= DriverManager.getConnection("jdbc:sqlite:src/crawler.db");
	    	System.out.println("Opened database successfully");
	    	if (conn != null) {  
	            DatabaseMetaData meta = conn.getMetaData();  
	            System.out.println("The driver name is " + meta.getDriverName());   
	        }  
	    	stmt = conn.createStatement();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	}

public void CreateDB() {
	try {
        ds = new SQLiteDataSource();

        ds.setUrl("jdbc:sqlite:src/crawler1.db");

    } catch ( Exception e ) {
        e.printStackTrace();
        System.exit(0);
    }
	
	
	try {
		Class.forName("org.sqlite.JDBC");
		conn= DriverManager.getConnection("jdbc:sqlite:src/crawler1.db");
    	System.out.println("Opened database 2 successfully after creating");
    	if (conn != null) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new database has been created.");
        }
    	stmt = conn.createStatement();
    } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
    }



    int rv;
	try {
        String sql = "CREATE TABLE IF NOT EXISTS crawler (\n"
                + " id integer PRIMARY KEY,\n"
                + " URL text NOT NULL,\n"
                + " PURL text NOT NULL\n"
                + ");";
		rv = stmt.executeUpdate( sql );
		 System.out.println( "executeUpdate() returned " + rv );
		 /*String query1 = "INSERT INTO crawler ( URL, PURL ) VALUES ( 'www.google.com', 'www/bing.com' )";
		 int rv1 = stmt.executeUpdate( query1 );
		    System.out.println( "1st executeUpdate() returned " + rv1 );
		    String query2 = "SELECT * FROM crawler";

		    ResultSet rs = stmt.executeQuery(query2);

		    while ( rs.next() ) {
		        int id = rs.getInt( "ID" );
		        String name = rs.getString( "URL" );
		        String purl = rs.getString( "PURL" );

		        System.out.println( "Result: ID = " + id + ", URL = " + name + " PURL = "+ purl );
		    }*/
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	connectClose();
	
}
public void display() {
	
    ResultSet rs = null;
	try {
		String query2 = "SELECT * FROM crawler";
		rs = stmt.executeQuery(query2);
		
	    while ( rs.next() ) {
	        int id = rs.getInt( "ID" );
	        String name = rs.getString( "URL" );
	        String purl = rs.getString( "PURL" );

	        System.out.println( "Result: ID = " + id + ", URL = " + name + " PURL = "+ purl );
	    }
	
	} catch (SQLException e) {
		e.printStackTrace();
	}

}


public ResultSet getResult() {
	
    ResultSet rs = null;
	try {
		String query2 = "SELECT * FROM crawler";
		rs = stmt.executeQuery(query2);
		
	   /* while ( rs.next() ) {
	        int id = rs.getInt( "ID" );
	        String name = rs.getString( "URL" );
	        String purl = rs.getString( "PURL" );

	        //System.out.println( "ID = " + id + ", URL = " + name + " PURL = "+ purl );
	    }*/
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return rs;
}

public void insertDB(String link, String plink) {
	
	String query1 = "INSERT INTO crawler ( URL, PURL ) VALUES ( ?, ?)";
	 int rv1;
	try {
		 PreparedStatement pstmt = ((java.sql.Connection) conn).prepareStatement(query1);  
		 pstmt.setString(1, link);  
       pstmt.setString(2, plink);  
            
		 rv1 = pstmt.executeUpdate();
		 
		 System.out.println("Insert return code: " + rv1);
		 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

public void connectClose() {
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void dropTable() {
	String query = "DROP TABLE IF EXISTS test";
	try {
		stmt.executeUpdate( query);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 System.out.println( "Table dropped " );
}

}
