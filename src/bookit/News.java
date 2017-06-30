package bookit;
//package bookit;
//
//import bookit.MbDb;
//import bookit.ServiceDAO;
//import java.sql.*;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.List;
//import java.util.Locale;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.model.SelectItem;
//
//@ManagedBean(name="services")
//@SessionScoped
//
//public class News {
//
//	/**
//	 * @return the newsID
//	 */
//	public int getNewsID() {
//		return newsID;
//	}
//
//	/**
//	 * @param newsID the newsID to set
//	 */
//	public void setNewsID(int newsID) {
//		this.newsID = newsID;
//	}
//
//	/**
//	 * @return the newsHeader
//	 */
//	public String getNewsHeader() {
//		return newsHeader;
//	}
//
//	/**
//	 * @param newsHeader the newsHeader to set
//	 */
//	public void setNewsHeader(String newsHeader) {
//		this.newsHeader = newsHeader;
//	}
//
//	/**
//	 * @return the newsContent
//	 */
//	public String getNewsContent() {
//		return newsContent;
//	}
//
//	/**
//	 * @param newsContent the newsContent to set
//	 */
//	public void setNewsContent(String newsContent) {
//		this.newsContent = newsContent;
//	}
//
//	private int newsID;
//	private String newsHeader, newsContent;
//	
//	public ArrayList<News> getMessages() throws SQLException{
//		return NewsDAO.getNews();
//	}
//}
