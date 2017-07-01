package bookit;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

	private ServiceObject selected;

	@PostConstruct
	public void init() {

		// add here check for already existing services in the list

		serviceObjects = serviceBean.getAllServicesFromDB();
		System.out.println("size" + serviceObjects.size());

	}

	public static String getServiceChosen() {
		return serviceChosen;
	}

	public static void setServiceChosen(String serviceChosen) {
		ServiceView.serviceChosen = serviceChosen;
	}

	public ServiceObject getSelected() {
		return selected;
	}

	public void setSelected(ServiceObject selected) throws IOException {

		this.selected = selected;

		System.out.println("selected item: " + selected.getName());
		serviceChosen = selected.getName();
		redirect();
	}

	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("calendar.xhtml");
	}

	public void setServiceBean(ServiceBean service) {
		this.serviceBean = service;

	}

	public ArrayList<ServiceObject> getServiceObjects() {
		return serviceObjects;
	}

	public void setServiceObjects(ArrayList<ServiceObject> serviceObjects) {
		this.serviceObjects = serviceObjects;

	}

}
