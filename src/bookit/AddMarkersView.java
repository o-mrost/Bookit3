package bookit;

import java.io.Serializable;
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
public class AddMarkersView implements Serializable {
     
    private MapModel emptyModel;
      
    private String title;
      
    private double lat;
      
    private double lng;
  
    /**
     * 
     */
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }
      
    /**
     * @return
     */
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    /**
     * @return
     */
    public String getTitle() {
        return title;
    }
  
    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
  
    /**
     * @return
     */
    public double getLat() {
        return lat;
    }
  
    /**
     * @param lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    /**
     * @return
     */
    public double getLng() {
        return lng;
    }
  
    /**
     * @param lng
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
      
    /**
     * 
     */
    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
}