package bookit;

import bookit.MbDb;
import bookit.ServiceDAO;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="services")
@SessionScoped

public class Service {
	
	static public void formatCurrency(Locale currentLocale, int cost) {

	    Currency currentCurrency = Currency.getInstance(currentLocale);
	    NumberFormat currencyFormatter = 
	        NumberFormat.getCurrencyInstance(currentLocale);
	    currencyFormatter.format(cost);
	}
	
	/**
	 * @return the serviceID
	 */
	public int getServiceID() {
		return serviceID;
	}


	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}


	/**
	 * @return the serviceKosten
	 */
	public int getServiceKosten() {
		return serviceKosten;
	}


	/**
	 * @param serviceKosten the serviceKosten to set
	 */
	public void setServiceKosten(int serviceKosten) {
		formatCurrency(Locale.GERMANY, serviceKosten);
		System.out.println("EUR: "+serviceKosten);
		this.serviceKosten = serviceKosten;
	}



	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}


	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	/**
	 * @return the serviceDauer
	 */
	public Time getServiceDauer() {
		return serviceDauer;
	}


	/**
	 * @param serviceDauer the serviceDauer to set
	 */
	public void setServiceDauer(Time serviceDauer) {
		this.serviceDauer = serviceDauer;
	}


	private int serviceID, serviceKosten;
	private String serviceName;
	private Time serviceDauer;

	
	public ArrayList<Service> getMessages() throws SQLException{
		return ServiceDAO.getServices();
	}
}
