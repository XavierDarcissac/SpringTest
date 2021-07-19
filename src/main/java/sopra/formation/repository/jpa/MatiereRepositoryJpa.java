package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

@Repository
@Transactional(readOnly = true)
public class MatiereRepositoryJpa implements IMatiereRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<Matiere> findAll() {
		TypedQuery<Matiere> query = em.createQuery("select m from Matiere m", Matiere.class);

		return query.getResultList();
	}

	public Matiere findById(Long id) {
		return em.find(Matiere.class, id);
	}

	@Transactional(readOnly = false)
	public Matiere save(Matiere obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Matiere obj) {
		em.remove(em.merge(obj));
	}

}
