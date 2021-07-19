package sopra.formation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdinateurId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="society", length = 50)
	private String societe;
	@Column(length = 10)
	private String code;

	public OrdinateurId() {
		super();
	}

	public OrdinateurId(String societe, String code) {
		super();
		this.societe = societe;
		this.code = code;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((societe == null) ? 0 : societe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdinateurId other = (OrdinateurId) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (societe == null) {
			if (other.societe != null)
				return false;
		} else if (!societe.equals(other.societe))
			return false;
		return true;
	}

	}
