package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Ordinateur;
import sopra.formation.model.OrdinateurId;
import sopra.formation.repository.IOrdinateurRepository;

@Repository
@Transactional(readOnly = true)
public class OrdinateurRepositoryJpa implements IOrdinateurRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ordinateur> findAll() {
		TypedQuery<Ordinateur> query = em.createQuery("from Ordinateur", Ordinateur.class);

		return query.getResultList();
	}

	@Override
	public Ordinateur findById(OrdinateurId id) {
		return em.find(Ordinateur.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public Ordinateur save(Ordinateur obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Ordinateur obj) {
		obj = em.merge(obj);

		em.remove(obj);
	}

}
