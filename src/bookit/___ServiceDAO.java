package bookit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ___ServiceDAO {
	public static ArrayList<___Service> getServices() throws SQLException {

		// establish databse connection
		MysqlDataSource mds = new MysqlDataSource();

		try {
			mds.setURL("jdbc:mysql://localhost/bookit");
			mds.setUser("root");
			mds.setPassword("");
			Connection connection = mds.getConnection();

			System.out.println("Connection established");

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM services");
			ArrayList<___Service> bl = new ArrayList<___Service>();
			ResultSet rs = ps.executeQuery();

			// System.out.println("Printing resultset...");
			// try {
			// Statement stmt = connection.createStatement();
			// String query = "select * from services ;";
			// //services is the table name
			// ResultSet rs = stmt.executeQuery(query);
			// while (rs.next()) {
			// String serviceName = rs.getObject(2).toString();
			// System.out.println("Name of the service is " + serviceName);
			//
			// }
			// } catch (SQLException e) {
			// e.printStackTrace();
			// for(Throwable ex : e) {
			// System.err.println("Error occurred " + ex);
			// }
			// System.out.println("Error in fetching data");
			// }

			boolean found = true;
			while (rs.next()) {
				___Service serviceList = new ___Service();
				serviceList.setID(rs.getInt("serviceID"));
				serviceList.setName(rs.getString("serviceName"));
				serviceList.setDauer(rs.getTime("serviceDauer"));
				serviceList.setKosten(rs.getInt("serviceKosten"));
				bl.add(serviceList);
				found = true;
			}

			bl.stream().forEach(System.out::println);

			if (found) {
				return bl;
			} else {
				return null; // if no entries are found
			}
		} catch (Exception e) {
			System.out.println("Error In getBookings() -->" + e.getMessage());
			return null;
		}

	}
}