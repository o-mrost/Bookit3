package bookit;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dtBasicView")
@ViewScoped
public class ServiceView implements Serializable {

	private ArrayList<ServiceObject> serviceObjects;

	@ManagedProperty("#{serviceService}")
	private ServiceService service;

	@PostConstruct
	public void init() {
		service.getAllServicesFromDB();
	}

	public ArrayList<ServiceObject> getServiceObjects() {
		return serviceObjects;
	}

	public void setService(ServiceService service) {
		this.service = service;
	}

}
