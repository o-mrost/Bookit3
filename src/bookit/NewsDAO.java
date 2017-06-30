package bookit;
//package bookit;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.ArrayList;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class NewsDAO {
//public static ArrayList<News> getNews() throws SQLException {
//
//	//establish databse connection
//	MysqlDataSource mds = new MysqlDataSource();
//	
//	try { mds.setURL("jdbc:mysql://localhost/bookit");
//	mds.setUser("root");
//	mds.setPassword("");
//	Connection connection = mds.getConnection();
//	
//	System.out.println("Connection established");
//	
//	PreparedStatement ps = connection.prepareStatement("SELECT * FROM news");
//	ArrayList<News> nl = new ArrayList<News>();
//	ResultSet rs = ps.executeQuery();
//	
//	boolean found = true;
//        while (rs.next()) {
//            News newsList = new News();
//            newsList.setNewsID(rs.getInt("serviceID"));
//            newsList.setNewsHeader(rs.getString("serviceName"));
//            newsList.setNewsContent(rs.getString("serviceName"));
//            nl.add(newsList);
//            found = true;
//        }
//        
//        nl.stream().forEach(System.out::println);
//        
//	if (found) {
//	    return nl;
//	} else {
//	   return null; // if no entries are found
//	}
//   } catch (Exception e) {
//	System.out.println("Error In getBookings() -->" + e.getMessage());
//	return (null);
//   }
//
//}
//}
//	