package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "computer")
@IdClass(OrdinateurId.class)
public class Ordinateur {
	@Id
	@Column(name = "society", length = 50)
	private String societe;
	@Id
	@Column(length = 10)
	private String code;
	@Version
	private int version;
	private int ram;
	private boolean ssd;
	private String cpu;

	public Ordinateur() {
		super();
	}

	public Ordinateur(String societe, String code, int ram, boolean ssd, String cpu) {
		super();
		this.societe = societe;
		this.code = code;
		this.ram = ram;
		this.ssd = ssd;
		this.cpu = cpu;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public boolean isSsd() {
		return ssd;
	}

	public void setSsd(boolean ssd) {
		this.ssd = ssd;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

}
