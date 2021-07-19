package sopra.formation.repository;

import java.util.List;

import sopra.formation.model.Filiere;
import sopra.formation.model.Salle;

public interface ISalleRepository extends IRepository<Salle, Long> {
	List<Salle> findAllByFiliere(Filiere filiere); // TODO
	List<Salle> findAllByVille(String ville); // TODO
}
