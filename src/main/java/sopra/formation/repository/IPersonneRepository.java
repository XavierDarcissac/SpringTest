package sopra.formation.repository;

import java.util.List;

import sopra.formation.model.Formateur;
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;

public interface IPersonneRepository extends IRepository<Personne, Long> {
	List<Stagiaire> findAllStagiaire(); // TODO

	List<Formateur> findAllFormateur(); // TODO

	Stagiaire findStagiaireByEmail(String email); // TODO

	Formateur findFormateurByEmail(String email); // TODO
	
	List<Formateur> findAllFormateur(Long idMatiere);
}
