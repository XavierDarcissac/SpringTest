package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.OrdinateurId;
import sopra.formation.model.OrdinateurWithEmbeddedId;
import sopra.formation.repository.IOrdinateurWithEmbeddedIdRepository;

@Repository
@Transactional(readOnly = true)
public class OrdinateurWithEmbeddedIdRepositoryJpa implements IOrdinateurWithEmbeddedIdRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<OrdinateurWithEmbeddedId> findAll() {
		TypedQuery<OrdinateurWithEmbeddedId> query = em.createQuery("from OrdinateurWithEmbeddedId",
				OrdinateurWithEmbeddedId.class);

		return query.getResultList();
	}

	@Override
	public OrdinateurWithEmbeddedId findById(OrdinateurId id) {
		return em.find(OrdinateurWithEmbeddedId.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public OrdinateurWithEmbeddedId save(OrdinateurWithEmbeddedId obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(OrdinateurWithEmbeddedId obj) {
		obj = em.merge(obj);

		em.remove(obj);
	}

}
