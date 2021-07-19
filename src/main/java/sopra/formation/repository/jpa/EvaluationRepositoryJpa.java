package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Evaluation;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IEvaluationRepository;

@Repository
@Transactional(readOnly = true)
public class EvaluationRepositoryJpa implements IEvaluationRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Evaluation> findAll() {
		TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e", Evaluation.class);

		return query.getResultList();
	}

	public Evaluation findById(Long id) {
		return em.find(Evaluation.class, id);
	}

	@Transactional(readOnly = false)
	public Evaluation save(Evaluation obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Evaluation obj) {
		em.remove(em.merge(obj));
	}

	public Evaluation findByStagiaireId(Long idStagiaire) {
		TypedQuery<Evaluation> query = em.createQuery("select s.evaluation from Stagiaire s where s.id = :idStagiaire",
				Evaluation.class);

		return query.getSingleResult();
	}

	@Override
	public List<Evaluation> findAllByTechnique(Integer technique) {
		TypedQuery<Evaluation> query = em.createNamedQuery("Evaluation.findAllByTechnique", Evaluation.class);

		query.setParameter("technique", technique);

		return query.getResultList();
	}

	@Override
	public Object[] findEvaluationRawByStagiaire(Stagiaire stagiaire) {
		TypedQuery<Object[]> query = em.createQuery(
				"select e.comportemental, e.technique, e.commentaires from Stagiaire s join s.evaluation e where s = :stag",
				Object[].class);

		query.setParameter("stag", stagiaire);

		return query.getSingleResult();
	}

}
