package sopra.formation.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "computer_embedded")
public class OrdinateurWithEmbeddedId {
	@EmbeddedId
	private OrdinateurId id;
	@Version
	private int version;
	private int ram;
	private boolean ssd;
	private String cpu;

	public OrdinateurWithEmbeddedId() {
		super();
	}
	
	public OrdinateurWithEmbeddedId(int ram, boolean ssd, String cpu) {
		super();
		this.ram = ram;
		this.ssd = ssd;
		this.cpu = cpu;
	}

	public OrdinateurId getId() {
		return id;
	}

	public void setId(OrdinateurId id) {
		this.id = id;
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
