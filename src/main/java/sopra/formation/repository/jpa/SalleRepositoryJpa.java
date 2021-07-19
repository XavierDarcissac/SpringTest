package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Filiere;
import sopra.formation.model.Salle;
import sopra.formation.repository.ISalleRepository;

@Repository
@Transactional(readOnly = true)
public class SalleRepositoryJpa implements ISalleRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Salle> findAll() {
		TypedQuery<Salle> query = em.createQuery("select s from Salle s", Salle.class);

		return query.getResultList();
	}

	public Salle findById(Long id) {
		return em.find(Salle.class, id);
	}

	@Transactional(readOnly = false)
	public Salle save(Salle obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Salle obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public List<Salle> findAllByFiliere(Filiere filiere) {
//			TypedQuery<Salle> query = em.createQuery("select s from Salle s join s.ues ue join ue.filiere f where f = :filiere", Salle.class);

//			TypedQuery<Salle> query = em.createQuery("select s from Salle s join s.ues ue where ue.filiere = :filiere", Salle.class);

		TypedQuery<Salle> query = em.createQuery("select ue.salle from UE ue where ue.filiere = :filiere", Salle.class);

		query.setParameter("filiere", filiere);

		return query.getResultList();
	}

	@Override
	public List<Salle> findAllByVille(String ville) {
		TypedQuery<Salle> query = em.createQuery("select s from Salle s where s.adr.ville = :ville", Salle.class);

		query.setParameter("ville", ville);

		return query.getResultList();
	}
}
