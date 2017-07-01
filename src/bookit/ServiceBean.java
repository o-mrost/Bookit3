package bookit;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "serviceBean")
@ApplicationScoped
public class ServiceBean {

	private Util util = new Util();
	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;
	
	private ArrayList<ServiceObject> listOfServices = new ArrayList<>();

	public ArrayList<ServiceObject> getAllServicesFromDB() {
		System.out.println("load info from db");

		String SQL_SELECT = "SELECT * FROM services";

		if (util != null)
			con = util.getCon();
		if (con != null) {
			try {

				stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = stm.executeQuery(SQL_SELECT);

				while (rs.next()) {

					ServiceObject newService = new ServiceObject();

					newService.setID(rs.getInt("serviceID"));
					newService.setName(rs.getString("serviceName"));
					newService.setDauer(rs.getTime("serviceDauer"));
					newService.setKosten(rs.getInt("serviceKosten"));

					listOfServices.add(newService);

				}

				System.out.println("total length: " + listOfServices.size());
				
				for (ServiceObject ser : listOfServices) {
					System.out.println(
							"services: " + ser.getName() + ", " + ser.getKosten() + ", " + ser.getID() + ", " + ser.getDauer());
				}

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
		
		return listOfServices;

	}

}
