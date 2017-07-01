package bookit;

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

	private ArrayList<ServiceObject> serviceObjects;

	@ManagedProperty("#{serviceBean}")
	private ServiceBean serviceBean;

	private ServiceObject selected;

	public ServiceObject getSelected() {
		return selected;
	}

	public void setSelected(ServiceObject selected) {

		this.selected = selected;

		System.out.println("selected item: " + selected.getName());
		redirectToCalendar();
	}

	@PostConstruct
	public void init() {

		// add here check for already existing services in the list

		serviceObjects = serviceBean.getAllServicesFromDB();
		System.out.println("size" + serviceObjects.size());

	}

	public void redirectToCalendar() {

		// add here redirect to the calendar page (with string and navigation
		// rules may be)

		System.out.println("redirect with string: " + selected.getName());

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
