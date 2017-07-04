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

	

	private ArrayList<ServiceObject> serviceObjectsToDisplay;

	/**
	 * @return
	 */
	public ArrayList<ServiceObject> getServiceObjectsToDisplay() {
		return serviceObjectsToDisplay;
	}

	/**
	 * @param serviceObjectsToDisplay
	 */
	public void setServiceObjectsToDisplay(ArrayList<ServiceObject> serviceObjectsToDisplay) {
		this.serviceObjectsToDisplay = serviceObjectsToDisplay;
	}

	/**
	 * 
	 */
	@ManagedProperty("#{serviceBean}")
	private ServiceBean serviceBean;

	/**
	 * 
	 */
	private ServiceObject selected;

	/**
	 * 
	 */
	public int initialSize;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {

		// add here check for already existing services in the list

		// serviceBean.getListOfServices();

		serviceObjects = serviceBean.getAllServicesFromDB();
		//
		// // serviceObjectsToDisplay = serviceBean.getAllServicesFromDB();
		//
		// System.out.println("total length, check here! " +
		// serviceBean.getSizeOfList());
		//
		// Collection<ServiceObject> collection = serviceObjects;
		//
		// System.out.println("size of collection: " + collection.size());

	}

	// public void showAllServices() {
	// serviceObjects = serviceBean.getAllServicesFromDB();
	// System.out.println("size" + serviceObjects.size());
	//
	// }

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
	 * @return
	 */
	public ServiceObject getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 * @throws IOException
	 */
	public void setSelected(ServiceObject selected) throws IOException {

		this.selected = selected;

		System.out.println("selected item: " + selected.getName());
		serviceChosen = selected.getName();
		redirect();
	}

	/**
	 * @throws IOException
	 */
	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("calendar.xhtml");
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

		System.out.println("getter from view");
		return serviceObjects;
	}

	/**
	 * @param serviceObjects
	 */
	public void setServiceObjects(ArrayList<ServiceObject> serviceObjects) {
		this.serviceObjects = serviceObjects;

	}

}
