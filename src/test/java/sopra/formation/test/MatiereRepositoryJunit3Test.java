package sopra.formation.test;

import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

public class MatiereRepositoryJunit3Test extends TestCase {
	
	private ClassPathXmlApplicationContext context;
	private IMatiereRepository matiereRepo;
	
	@Override
	protected void setUp() throws Exception {
		this.context = new ClassPathXmlApplicationContext("application-context.xml");
		this.matiereRepo = context.getBean(IMatiereRepository.class);
	}

	@Override
	protected void tearDown() throws Exception {
		this.context.close();
	}

	public void testCreateAndFindById() {
		System.out.println("testCreate Début ###################");
		
		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);
		
		Matiere htmlFind = matiereRepo.findById(html.getId());
		
		Assert.assertEquals("HTML", htmlFind.getNom());
		
		Assert.assertEquals((Integer) 2, htmlFind.getDuree());
		
		System.out.println("testCreate Fin ###################");
	}
	
	public void testModify() {
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
	
	public void testDelete() {
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
	
	public void testFindAll() {
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
