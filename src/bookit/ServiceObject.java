package bookit;

import java.sql.Time;

public class ServiceObject {

	private int iD, kosten;
	private String name;
	private Time dauer;

	/**
	 * @return
	 */
	public int getID() {
		return iD;
	}

	/**
	 * @param iD
	 */
	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * @return
	 */
	public int getKosten() {
		return kosten;
	}

	/**
	 * @param kosten
	 */
	public void setKosten(int kosten) {
		this.kosten = kosten;
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
	public Time getDauer() {
		return dauer;
	}

	/**
	 * @param dauer
	 */
	public void setDauer(Time dauer) {
		this.dauer = dauer;
	}

}
