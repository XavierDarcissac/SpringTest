package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Filiere;
import sopra.formation.repository.IFiliereRepository;

@Repository
@Transactional(readOnly = true)
public class FiliereRepositoryJpa implements IFiliereRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Filiere> findAll() {
		TypedQuery<Filiere> query = em.createQuery("select f from Filiere f", Filiere.class);

		return query.getResultList();
	}

	public Filiere findById(Long id) {
		return em.find(Filiere.class, id);
	}

	@Transactional(readOnly = false)
	public Filiere save(Filiere obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Filiere obj) {
		em.remove(em.merge(obj));
	}

	public Filiere findByPromotion(String promotion) {
		TypedQuery<Filiere> query = em.createQuery(
				"select distinct f from Filiere f left join fetch f.stagiaires s where f.promotion = :promo",
				Filiere.class);

		query.setParameter("promo", promotion);

		return query.getSingleResult();
	}

	@Override
	public List<Filiere> findAllByVille(String ville) {
//			TypedQuery<Filiere> query = em.createQuery("select f from Filiere f join f.ues ue join ue.salle s join s.adr a where a.ville = :ville", Filiere.class);

		TypedQuery<Filiere> query = em.createQuery("select ue.filiere from UE ue where ue.salle.adr.ville = :ville",
				Filiere.class);

		query.setParameter("ville", ville);

		return query.getResultList();
	}

	@Override
	public Filiere findByIdWithReferent(Long id) {
		TypedQuery<Filiere> query = em
				.createQuery("select f from Filiere f left join fetch f.referent r where f.id = :id", Filiere.class);

		query.setParameter("id", id);

		return query.getSingleResult();
	}

}
