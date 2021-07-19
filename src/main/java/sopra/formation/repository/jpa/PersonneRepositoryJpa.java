package sopra.formation.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sopra.formation.model.Formateur;
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IPersonneRepository;

@Repository
@Transactional(readOnly = true)
public class PersonneRepositoryJpa implements IPersonneRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Personne> findAll() {
		TypedQuery<Personne> query = em.createQuery("select p from Personne p", Personne.class);

		return query.getResultList();
	}

	public Personne findById(Long id) {
		return em.find(Personne.class, id);
	}

	@Transactional(readOnly = false)
	public Personne save(Personne obj) {
		return em.merge(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Personne obj) {
		em.remove(em.merge(obj));
	}

	@Override
	public List<Stagiaire> findAllStagiaire() {
		TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s", Stagiaire.class);

		return query.getResultList();
	}

	@Override
	public List<Formateur> findAllFormateur() {
		TypedQuery<Formateur> query = em.createQuery("select f from Formateur f", Formateur.class);

		return query.getResultList();
	}

	@Override
	public Stagiaire findStagiaireByEmail(String email) {
		TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s where s.email = :email",
				Stagiaire.class);

		query.setParameter("email", email);

		return query.getSingleResult();
	}

	@Override
	public Formateur findFormateurByEmail(String email) {
		TypedQuery<Formateur> query = em.createQuery("select f from Formateur f where f.email = :email",
				Formateur.class);

		query.setParameter("email", email);

		return query.getSingleResult();
	}

	@Override
	public List<Formateur> findAllFormateur(Long idMatiere) {
//			TypedQuery<Formateur> query = em.createQuery("select f from Formateur f join f.competences m where m.id = :id", Formateur.class);
		TypedQuery<Formateur> query = em.createQuery("select f from Matiere m join m.formateurs f where m.id = :id",
				Formateur.class);

		query.setParameter("id", idMatiere);

		return query.getResultList();
	}

}
