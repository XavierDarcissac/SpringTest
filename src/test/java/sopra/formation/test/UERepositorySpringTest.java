package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.Salle;
import sopra.formation.model.UE;
import sopra.formation.repository.IFiliereRepository;
import sopra.formation.repository.IMatiereRepository;
import sopra.formation.repository.IPersonneRepository;
import sopra.formation.repository.ISalleRepository;
import sopra.formation.repository.IUERepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class UERepositorySpringTest {

	@Autowired
	private IUERepository ueRepo;
	@Autowired
	private IPersonneRepository personneRepo;
	@Autowired
	private IFiliereRepository filiereRepo;
	@Autowired
	private IMatiereRepository matiereRepo;
	@Autowired
	private ISalleRepository salleRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Formateur formateur1 = new Formateur();
		formateur1 = (Formateur) personneRepo.save(formateur1);
		
		Filiere filiere1 = new Filiere();
		filiere1 = filiereRepo.save(filiere1);

		Matiere matiere1 = new Matiere();
		matiere1 = matiereRepo.save(matiere1);
		
		Salle salle1 = new Salle();
		salle1 = salleRepo.save(salle1);
		
		UE uehtml = new UE(2150, 6, 1);
		uehtml.setFormateur(formateur1);
		uehtml.setFiliere(filiere1);
		uehtml.setMatiere(matiere1);
		uehtml.setSalle(salle1);
		
		uehtml = ueRepo.save(uehtml);

		UE uehtmlFind = ueRepo.findById(uehtml.getId());

		Assert.assertEquals((Integer) 2150, uehtmlFind.getCode());
		Assert.assertEquals((Integer) 6, uehtmlFind.getDuree());
		Assert.assertEquals(1, uehtmlFind.getOrdre());
		Assert.assertEquals(formateur1.getId(), uehtmlFind.getFormateur().getId()); // verifier que l'id du formateur recup est le meme que le créé
		
		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		UE uehtml = new UE(2150, 6, 1);
		uehtml = ueRepo.save(uehtml);

		UE uehtmlFind = ueRepo.findById(uehtml.getId());

		uehtmlFind.setCode(3587);
		uehtmlFind.setDuree(3);

		uehtmlFind = ueRepo.save(uehtmlFind);
		uehtmlFind = ueRepo.findById(uehtmlFind.getId());

		Assert.assertEquals((Integer) 3587, uehtmlFind.getCode());
		Assert.assertEquals((Integer) 3, uehtmlFind.getDuree());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		UE uehtml = new UE(2150, 6, 1);
		uehtml = ueRepo.save(uehtml);

		UE uehtmlFind = ueRepo.findById(uehtml.getId());

		ueRepo.delete(uehtmlFind);

		uehtmlFind = ueRepo.findById(uehtml.getId());

		Assert.assertNull(uehtmlFind);

//		if(htmlFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = ueRepo.findAll().size();

		UE uehtml = new UE(2150, 6, 1);

		uehtml = ueRepo.save(uehtml);

		UE ue2 = new UE(5627, 4, 2);

		ue2 = ueRepo.save(ue2);

		int sizeEnd = ueRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
