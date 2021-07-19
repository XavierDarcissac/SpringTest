package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.UE;
import sopra.formation.repository.IUERepository;

@Repository
@Transactional(readOnly = true)
public class UERepositoryJpa implements IUERepository {

	@PersistenceContext
	private EntityManager em;

	public List<UE> findAll() {
		TypedQuery<UE> query = em.createQuery("select ue from UE ue", UE.class);

		return query.getResultList();
	}

	public UE findById(Long id) {
		return em.find(UE.class, id);
	}

	@Transactional(readOnly = false)
	public UE save(UE obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(UE obj) {
		em.remove(em.merge(obj));
	}

}
