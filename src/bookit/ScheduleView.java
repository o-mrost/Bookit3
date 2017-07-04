package bookit;

import static java.lang.System.out;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {
	

	public LoginBeanNew loginBean = new LoginBeanNew();

	ServiceView view = new ServiceView();
	

	private Util util = new Util();
	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;

	private int id_booking = 0;
	private Date time_to = new Date(0L);
	private Date time_from = new Date(0L);
	private String comment;
	private String companyName = "";
	private String customerName;

	private String serviceSelected = null;
	private HtmlSelectOneMenu cbxSkin = new HtmlSelectOneMenu();
	private List<SelectItem> options = new ArrayList<SelectItem>();

	/**
	 * @return
	 */
	public List<SelectItem> getOptions() {
		// System.out.println("getOptions method");
		return options;
	}

	/**
	 * @param cbxSkin
	 */
	public void setCbxSkin(HtmlSelectOneMenu cbxSkin) {
		this.cbxSkin = cbxSkin;
	}

	/**
	 * @return
	 */
	public HtmlSelectOneMenu getCbxSkin() {
		// System.out.println("getCbxSkin methode");

		cbxSkin.setValue("Maerz");
		return cbxSkin;
	}

	/**
	 * @return
	 */
	public String getServiceSelected() {
		return serviceSelected;
	}

	/**
	 * @param serviceSelected
	 */
	public void setServiceSelected(String serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	/**
	 * 
	 */
	private TimeConvert convert = new TimeConvert();

	/**
	 * 
	 */
	String select;

	/**
	 * 
	 */
	String sql_select = "SELECT b.ID_Booking booking, b.Time_From timefrom, "
			+ "b.Time_To timeto, b.Comment comments, c.Company_Name compname, "
			+ "cus.Customer_Lastname custname FROM booking b join company c on "
			+ "b.ID_Company=c.ID_Company join customer cus on b.ID_Customer=cus.ID_Customer";

	/**
	 * 
	 */
	String sql_user = " WHERE cus.ID_Customer = ?";

	/**
	 * 
	 */
	private ScheduleModel eventModel;
	/**
	 * 
	 */
	private ScheduleEvent event = new DefaultScheduleEvent();

	// getters and setters

	/**
	 * @return
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @return
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return
	 */
	public java.util.Date getTime_from() {

		return time_from;
	}

	/**
	 * @param dt
	 */
	public void setTime_from(java.util.Date dt) {
		if (dt != null)
			time_from = new Date(dt.getTime());
		else
			time_from = new Date(0L);
	}

	/**
	 * @return
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return
	 */
	public java.util.Date getTime_to() {
		return time_to;
	}

	/**
	 * @param dt
	 */
	public void setTime_to(java.util.Date dt) {
		if (dt != null)
			time_to = new Date(dt.getTime());
		else
			time_to = new Date(0L);
	}

	/**
	 * @return
	 */
	public int getId_booking() {
		return id_booking;
	}

	/**
	 * @param id_booking
	 */
	public void setId_booking(int id_booking) {
		this.id_booking = id_booking;
	}

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

		connectToDb();

		options.add(new SelectItem("first value", "first treatment", "Description_1"));
		options.add(new SelectItem("bart value", "bart", "Description_2"));
		options.add(new SelectItem("welle value", "dauerwelle", "Description_3"));
	}

	/**
	 * @param vce
	 */
	public void cbxChangeListner(ValueChangeEvent vce) {
		System.out.println("Deine Wahl: " + vce.getNewValue());
	}

	/**
	 * 
	 */
	private void connectToDb() {
		System.out.println("load info from db");

		if (util != null)
			con = util.getCon();
		if (con != null) {
			try {

				if (loginBean.sOutcome == "user") {
					select = sql_select + sql_user;

					PreparedStatement ps = con.prepareStatement(select);
					ps.setInt(1, loginBean.userId);
					rs = ps.executeQuery();

				} else {
					select = sql_select;

					stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = stm.executeQuery(select);
				}

				System.out.println("+++++ select" + "" + select);

				while (rs.next()) {
					displayAllAppointmentData();
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
	}

	/**
	 * @throws SQLException
	 */
	private void displayAllAppointmentData() throws SQLException {

		setId_booking(rs.getInt("booking"));
		setCompanyName(rs.getString("compname"));
		setCustomerName(rs.getString("custname"));
		setTime_to(rs.getTimestamp("timeto"));
		setComment(rs.getString("comments"));
		setTime_from(rs.getTimestamp("timefrom"));

		eventModel.addEvent(new DefaultScheduleEvent(comment, time_from, time_to));

	}

	/**
	 * @param actionEvent
	 * @throws ParseException
	 */
	public void insert(ActionEvent actionEvent) throws ParseException {

		if (event.getId() == null) {

			String sql_insert = "INSERT INTO booking (ID_Company, ID_Customer, Time_From, Time_To, Comment) VALUES (?, ?, ?, ?, ?)";

			try {

				String timeFrom = convert.convertTime(event.getStartDate().toString());
				String timeTo = convert.convertTime(event.getEndDate().toString());

				// convert java String to sql date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				java.util.Date date = sdf.parse(timeFrom);
				java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());

				java.util.Date date2 = sdf.parse(timeTo);
				java.sql.Timestamp sqlEndDate = new java.sql.Timestamp(date2.getTime());

				PreparedStatement ps = con.prepareStatement(sql_insert);

				ps.setInt(1, 1);

				if (loginBean.isAdminLoggedIn()) {
					ps.setInt(2, 99);
				} else {
					ps.setInt(2, loginBean.getUserId());
				}

				ps.setTimestamp(3, sqlStartDate);
				ps.setTimestamp(4, sqlEndDate);
				ps.setString(5, event.getTitle());

				int n = ps.executeUpdate();
				if (n == 1) {
					out.println("O.K., Datensatz eingefügt.");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"O. K.", "Ein Datensatz erfolgreich eingefügt."));
				}

				ps.close();

			} catch (SQLException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "SQLException", ex.getLocalizedMessage()));
				out.println("Error: " + ex);
				ex.printStackTrace();
			}

			eventModel.addEvent(event);
		} else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();

	}

	/**
	 * @param selectEvent
	 */
	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	/**
	 * @param selectEvent
	 */
	public void onDateSelect(SelectEvent selectEvent) {

		System.out.println("+++++ new string: " + view.getServiceChosen());

		event = new DefaultScheduleEvent(view.getServiceChosen(), (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

}