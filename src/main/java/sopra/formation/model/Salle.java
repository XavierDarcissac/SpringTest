package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "classroom")
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(name = "name", length = 50)
	private String nom;
	@Column(name = "capacity")
	private Integer capacite;
	@Column(name = "projector")
	private Boolean videoProjecteur;
	@Embedded
	private Adresse adr;
	@OneToMany(mappedBy = "salle")
	private List<UE> ues = new ArrayList<UE>();

	public Salle() {
		super();
	}

	public Salle(String nom, Integer capacite, Boolean videoProjecteur) {
		super();
		this.nom = nom;
		this.capacite = capacite;
		this.videoProjecteur = videoProjecteur;
	}

	public Salle(Long id, String nom, Integer capacite, Boolean videoProjecteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.videoProjecteur = videoProjecteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Boolean getVideoProjecteur() {
		return videoProjecteur;
	}

	public void setVideoProjecteur(Boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}

	public Adresse getAdr() {
		return adr;
	}

	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	public List<UE> getUes() {
		return ues;
	}

	public void setUes(List<UE> ues) {
		this.ues = ues;
	}

	public void addUe(UE ue) {
		this.ues.add(ue);
	}

	@Override
	public String toString() {
		return "Salle [nom=" + nom + ", capacite=" + capacite + ", videoProjecteur=" + videoProjecteur + ", adr=" + adr
				+ "]";
	}

}
