package bookit;

import java.sql.Time;

public class ServiceObject {

	private int iD, kosten;
	private String name;
	private Time dauer;

	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getDauer() {
		return dauer;
	}

	public void setDauer(Time dauer) {
		this.dauer = dauer;
	}

}
