package sopra.formation.repository;

import java.util.List;

import sopra.formation.model.Evaluation;
import sopra.formation.model.Stagiaire;

public interface IEvaluationRepository extends IRepository<Evaluation, Long> {
	Evaluation findByStagiaireId(Long idStagiaire);	// TODO

	List<Evaluation> findAllByTechnique(Integer technique);
	
	Object[] findEvaluationRawByStagiaire(Stagiaire stagiaire);
}
