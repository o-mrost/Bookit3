package bookit;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "dtBasicView")
@ViewScoped
public class ServiceView implements Serializable {

	static String serviceChosen;
	private ArrayList<ServiceObject> serviceObjects;

	@ManagedProperty("#{serviceBean}")
	private ServiceBean serviceBean;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		serviceObjects = serviceBean.getAllServicesFromDB();
	}

	/**
	 * sets item selected from datatable on userPage or adminPage 
	 * @param selected
	 * @throws IOException
	 */
	public void setSelected(ServiceObject selected) throws IOException {

		System.out.println("selected item: " + selected.getName());
		serviceChosen = selected.getName();
		redirect();
	}

	/**
	 * redirects to the calendar.xhtml page
	 * 
	 * @throws IOException
	 */
	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("calendar.xhtml");
	}

	/**
	 * @return
	 */
	public static String getServiceChosen() {
		return serviceChosen;
	}

	/**
	 * @param serviceChosen
	 */
	public static void setServiceChosen(String serviceChosen) {
		ServiceView.serviceChosen = serviceChosen;
	}

	/**
	 * @param service
	 */
	public void setServiceBean(ServiceBean service) {
		this.serviceBean = service;

	}

	/**
	 * @return
	 */
	public ArrayList<ServiceObject> getServiceObjects() {
		return serviceObjects;
	}

	/**
	 * @param serviceObjects
	 */
	public void setServiceObjects(ArrayList<ServiceObject> serviceObjects) {
		this.serviceObjects = serviceObjects;
	}

}
