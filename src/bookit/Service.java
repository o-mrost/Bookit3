package bookit;

import bookit.MbDb;
import bookit.ServiceDAO;

import static java.lang.System.out;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultScheduleModel;

@ManagedBean(name = "services")
@SessionScoped

public class Service {

	private int iD, kosten;
	private String name;
	private Time dauer;

	private ArrayList<Service> listOfServices = new ArrayList<>();

	private Util util = new Util();

	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;

	static String newString = "newSting";

	@PostConstruct
	public void init() {
		connectToDb();
	}

	public void newMethod() {
		System.out.println("new method: " + newString);
	}

	private void connectToDb() {
		System.out.println("load info from db");

		String SQL_SELECT = "SELECT * FROM services";

		if (util != null)
			con = util.getCon();
		if (con != null) {
			try {
				stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stm.executeQuery(SQL_SELECT);
				// Service serviceList = new Service();

				while (rs.next()) {

					// serviceList.setID(rs.getInt("serviceID"));
					// serviceList.setName(rs.getString("serviceName"));
					// serviceList.setDauer(rs.getTime("serviceDauer"));
					// serviceList.setKosten(rs.getInt("serviceKosten"));
					//
					// listOfServices.add(serviceList);
					//
					// System.out.println("id: " + serviceList.iD + ", name: " +
					// serviceList.name + ", dauer: "
					// + serviceList.dauer + ", kosten: " + serviceList.kosten);

					setID(rs.getInt("serviceID"));
					setName(rs.getString("serviceName"));
					setDauer(rs.getTime("serviceDauer"));
					setKosten(rs.getInt("serviceKosten"));
				}
				// listOfServices.add(serviceList);

				System.out.println("list: " + listOfServices);

			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "SQLException", ex.getLocalizedMessage()));
				out.println("Error: " + ex);
				ex.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Exception", "Keine Verbindung zur Datenbank (Treiber nicht gefunden?)"));
			out.println("Keine Verbingung zur Datenbank");
		}
	}

	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name: " + name);
		this.name = name;
	}

	public Time getDauer() {
		return dauer;
	}

	public void setDauer(Time dauer) {
		this.dauer = dauer;
	}

	public ArrayList<Service> getMessages() throws SQLException {

		System.out.println("return value: " + listOfServices);
		return listOfServices;
	}
}
