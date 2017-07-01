package bookit;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dtBasicView")
@ViewScoped
public class BasicView implements Serializable {

	private List<Car> cars;

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@ManagedProperty("#{carService}")
	private CarService service;

	public CarService getService() {
		return service;
	}

	@PostConstruct
	public void init() {
		cars = service.createCars(5);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setService(CarService service) {
		this.service = service;
	}
}