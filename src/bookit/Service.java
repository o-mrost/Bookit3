package bookit;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private String newString = "newSting";

	public String getNewString() {
		return newString;
	}

	public void setNewString(String newString) {
		this.newString = newString;
	}

	@PostConstruct
	public void init() {
		connectToDb();
	}

	public void newMethod() {
		System.out.println("new method: ");
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

				while (rs.next()) {

					Service newService = new Service();

					newService.setID(rs.getInt("serviceID"));
					newService.setName(rs.getString("serviceName"));
					newService.setDauer(rs.getTime("serviceDauer"));
					newService.setKosten(rs.getInt("serviceKosten"));

					listOfServices.add(newService);

				}

				System.out.println("total length: " + listOfServices.size());

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
		this.name = name;
	}

	public Time getDauer() {
		return dauer;
	}

	public void setDauer(Time dauer) {
		this.dauer = dauer;
	}

	public ArrayList<Service> getMessages() throws SQLException {

		for (Service ser : listOfServices) {
			System.out.println(
					"services: " + ser.getName() + ", " + ser.getKosten() + ", " + ser.getID() + ", " + ser.getDauer());
		}

		return listOfServices;
	}
}
