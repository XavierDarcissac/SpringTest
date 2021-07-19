package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class MatiereRepositorySpringTest {

	@Autowired
	private IMatiereRepository matiereRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		Matiere htmlFind = matiereRepo.findById(html.getId());

		Assert.assertEquals("HTML", htmlFind.getNom());

		Assert.assertEquals((Integer) 2, htmlFind.getDuree());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		Matiere htmlFind = matiereRepo.findById(html.getId());

		htmlFind.setNom("HTML Avancée");
		htmlFind.setDuree(3);

		htmlFind = matiereRepo.save(htmlFind);

		htmlFind = matiereRepo.findById(htmlFind.getId());

		Assert.assertEquals("HTML Avancée", htmlFind.getNom());

		Assert.assertEquals((Integer) 3, htmlFind.getDuree());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		Matiere htmlFind = matiereRepo.findById(html.getId());

		matiereRepo.delete(htmlFind);

		htmlFind = matiereRepo.findById(html.getId());

		Assert.assertNull(htmlFind);

//		if(htmlFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = matiereRepo.findAll().size();

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		Matiere js = new Matiere("JAVASCRIPT", 3);

		js = matiereRepo.save(js);

		int sizeEnd = matiereRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
