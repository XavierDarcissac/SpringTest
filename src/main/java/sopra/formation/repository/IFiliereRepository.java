package sopra.formation.repository;

import java.util.List;

import sopra.formation.model.Filiere;

public interface IFiliereRepository extends IRepository<Filiere, Long> {
	Filiere findByIdWithReferent(Long id);
	Filiere findByPromotion(String promotion); // TODO
	List<Filiere> findAllByVille(String ville); // TODO
}
