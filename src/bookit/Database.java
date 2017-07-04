package bookit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {
	
	/**
	 * @return
	 */
	public static Connection getConnection(){
		System.out.println("Hello World this is freaking awesome");
		
		MysqlDataSource mds = new MysqlDataSource();
		
		try { mds.setURL("jdbc:mysql://localhost/bookit");
		mds.setUser("root");
		mds.setPassword("");
		Connection con = mds.getConnection();
		
		System.out.println("Connection established");
		
		try {
	        Statement stmt = con.createStatement();
	        String query = "select * from services ;";
	        //services is the table name
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String serviceName = rs.getObject(2).toString();
	            System.out.println("Name of the service is " + serviceName);

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        for(Throwable ex : e) {
	            System.err.println("Error occurred " + ex);
	        }
	        System.out.println("Error in fetching data");
	    }
		
		} catch(SQLException ex){
			while(ex!=null){
				ex.printStackTrace();
				ex = ex.getNextException();
		}
		}
		return null;
	}
}
