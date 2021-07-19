package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("trainer")
public class Formateur extends Personne {
	@Column(name = "executive")
	private Boolean referent;
	private Integer experience;
	@OneToMany(mappedBy = "formateur")
	private List<UE> ues = new ArrayList<UE>();
	@ManyToMany
	@JoinTable(name = "skills", joinColumns = @JoinColumn(name = "trainer_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"trainer_id", "subject_id" }))
	private List<Matiere> competences = new ArrayList<Matiere>();

	public Formateur() {
		super();
	}

	public Formateur(String email) {
		super(email);
	}

	public Formateur(Civilite civilite, String nom, String prenom, String email, String telephone, Boolean referent,
			Integer experience) {
		super(civilite, nom, prenom, email, telephone);
		this.referent = referent;
		this.experience = experience;
	}

	public Boolean getReferent() {
		return referent;
	}

	public void setReferent(Boolean referent) {
		this.referent = referent;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
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

	public List<Matiere> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Matiere> competences) {
		this.competences = competences;
	}

	public void addCompetence(Matiere matiere) {
		this.competences.add(matiere);
	}

	@Override
	public String toString() {
		return "Formateur [referent=" + referent + ", experience=" + experience + ", competences=" + competences + "]";
	}

}
