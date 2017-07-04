package bookit;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "lbn")
@SessionScoped

public class LoginBeanNew {

	private Util util = new Util();
	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;

	static String sOutcome = null;
	private boolean userLoggedIn = false;
	static boolean adminLoggedIn = false;
	static int userId;
	private String name = "";
	private String sqlUsername = "";
	private String sqlPassword = "";
	private boolean adminCheck = false;
	private String kennung = "";
	private String pw = "";
	private boolean loggedIn = false;

	/*--------------------------------------------------------------------------*/

	/**
	 * 
	 */
	public LoginBeanNew() {
		System.out.println("Mb.<init>...");
		System.out.println((new Date()).toString());
	}

	/*---------------------------------------------------------------------------------*/

	/**
	 * login method with password encryption
	 * @return
	 */
	public String actLoginNew() {

		System.out.println("actLogin()...");
		System.out.println("_______________________ admin login " + adminCheck);

		kennung = kennung.trim();
		pw = pw.trim();

		System.out.println("kennung: " + kennung);
		System.out.println("passwort: " + pw);

		String select;
		String selectUser = "SELECT ID_Customer, Customer_Lastname, Customer_Firstname,"
				+ " Customer_Login, Customer_Passwort FROM customer WHERE Customer_login = ?";

		String selectAdmin = "SELECT Company_Name, Company_Login, Company_Passwort FROM company "
				+ "WHERE Company_Login = ?";

		if (adminCheck) {
			select = selectAdmin;
		} else
			select = selectUser;

		if (util != null)
			con = util.getCon();
		if (con != null) {

			try {
				PreparedStatement ps = con.prepareStatement(select);
				ps.setString(1, kennung);

				rs = ps.executeQuery();

				if (rs.first())
					showData(adminCheck);

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

		pw = util.cryptpw(null, pw);

		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("Username: " + kennung);
		System.out.println("Password: " + pw);
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("sqlusername: " + sqlUsername + ", sql password: " + sqlPassword);
		System.out.println("++++++++++++++++++++++++++++++++");

		if (kennung.equalsIgnoreCase(sqlUsername) && pw.equals(sqlPassword)) {

			System.out.println("login successful");
			loggedIn = true;
		}

		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Fehler",
					"Kennung oder PW falsch (user/user oder admin/admin)"));

		System.out.println("USER EINGELOGGT: " + userLoggedIn);
		System.out.println("ADMIN EINGELOGGT: " + adminLoggedIn);

		if (userLoggedIn) {
			sOutcome = "user";
		}

		if (adminLoggedIn) {
			sOutcome = "admin";
		}

		System.out.println("angemeldet als: " + sOutcome);

		return sOutcome;
	}

	/**
	 * displays data for user or admin depending on who is logged in
	 * 
	 * @param admin
	 * @throws SQLException
	 */
	private void showData(boolean admin) throws SQLException {

		if (admin) {
			setName(rs.getString("company_name"));
			setSqlPassword(rs.getString("company_passwort"));
			setSqlUsername(rs.getString("company_login"));

			adminLoggedIn = true;
			userLoggedIn = false;

		} else {

			setName(rs.getString("customer_firstname") + " " + rs.getString("customer_lastname"));
			setSqlPassword(rs.getString("customer_passwort"));
			setSqlUsername(rs.getString("customer_login"));
			userId = rs.getInt("id_customer");

			userLoggedIn = true;
			adminLoggedIn = false;

		}
	}

	/**
	 * log out method return string is used in navigation rules to go back to
	 * loginPage.xhtml
	 * 
	 * @return
	 */
	public String logout() {
		System.out.println("logout ......");
		loggedIn = false;
		adminLoggedIn = false;
		userLoggedIn = false;
		sOutcome = "noone";
		return sOutcome;
	}

	/**
	 * sets locale to implement translation in german
	 * @param ae
	 */
	public void languageDE(ActionEvent ae) {
		System.out.println("deutsch");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.GERMAN);
	}

	/**
	 * sets locale to implement translation in german
	 * @param ae
	 */
	public void languageEN(ActionEvent ae) {
		System.out.println("english");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
	}

	/*-------------- getters and setters -------------------------------------------------*/

	/**
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public boolean isAdminCheck() {
		return adminCheck;
	}

	/**
	 * @param adminLogin
	 */
	public void setAdminCheck(boolean adminLogin) {
		this.adminCheck = adminLogin;
	}

	/**
	 * @return
	 */
	public String getSqlUsername() {
		return sqlUsername;
	}

	/**
	 * @param sqlUsername
	 */
	public void setSqlUsername(String sqlUsername) {
		this.sqlUsername = sqlUsername;
	}

	/**
	 * @return
	 */
	public String getSqlPassword() {
		return sqlPassword;
	}

	/**
	 * @param sqlPassword
	 */
	public void setSqlPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public boolean isUserLoggedIn() {
		return userLoggedIn;
	}

	/**
	 * @return
	 */
	public boolean isAdminLoggedIn() {
		return adminLoggedIn;
	}

	/**
	 * @param adminLoggedIn
	 */
	public void setAdminLoggedIn(boolean adminLoggedIn) {
		this.adminLoggedIn = adminLoggedIn;
	}

	/**
	 * @param s
	 */
	public void setKennung(String s) {
		kennung = s;
	}

	/**
	 * @return
	 */
	public String getKennung() {
		return kennung;
	}

	/**
	 * @param s
	 */
	public void setPw(String s) {
		pw = s;
	}

	/**
	 * @return
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return new Date();
	}

	/**
	 * @return
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

}
