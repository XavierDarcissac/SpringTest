package sopra.formation.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

public class MatiereRepositoryJunit4Test {
	
	private static ClassPathXmlApplicationContext context;
	private static IMatiereRepository matiereRepo;
	
	@BeforeClass
	public static void start() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		matiereRepo = context.getBean(IMatiereRepository.class);
	}

	@AfterClass
	public static void end() {
		context.close();
	}

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
		
		Assert.assertEquals((Integer)3, htmlFind.getDuree());
		
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
