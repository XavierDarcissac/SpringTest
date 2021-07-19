package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.OrdinateurId;
import sopra.formation.model.OrdinateurWithEmbeddedId;
import sopra.formation.repository.IOrdinateurWithEmbeddedIdRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class OrdinateurWithEmbeddedIdRepositorySpringTest {

	@Autowired
	private IOrdinateurWithEmbeddedIdRepository ordinateurWithEmbeddedIdRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		OrdinateurWithEmbeddedId o1 = new OrdinateurWithEmbeddedId(8,true, "I7");

		o1 = ordinateurWithEmbeddedIdRepo.save(o1);

		OrdinateurWithEmbeddedId o1Find = ordinateurWithEmbeddedIdRepo.findById(o1.getId());

		Assert.assertEquals(8, o1Find.getRam());
		Assert.assertEquals(true, o1Find.isSsd());
		Assert.assertEquals("I7", o1Find.getCpu());
		
		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		OrdinateurWithEmbeddedId o1 = new OrdinateurWithEmbeddedId( 8,true, "I7");

		o1 = ordinateurWithEmbeddedIdRepo.save(o1);

		OrdinateurWithEmbeddedId o1Find = ordinateurWithEmbeddedIdRepo.findById(o1.getId());

		o1Find.setRam(16);
		o1Find.setSsd(false);
		o1Find.setCpu("I1");
		
		
		
		o1Find = ordinateurWithEmbeddedIdRepo.save(o1Find);

		o1Find = ordinateurWithEmbeddedIdRepo.findById(new OrdinateurId(o1Find.getSociete(), o1Find.getCode()));

		Assert.assertEquals(16, o1Find.getRam());
		Assert.assertEquals(false, o1Find.isSsd());
		Assert.assertEquals("I1", o1Find.getCpu());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		OrdinateurWithEmbeddedId o1 = new OrdinateurWithEmbeddedId(8,true, "I7");

		o1 = ordinateurWithEmbeddedIdRepo.save(o1);

		OrdinateurWithEmbeddedId o1Find = ordinateurWithEmbeddedIdRepo.findById(o1.getId());

		ordinateurWithEmbeddedIdRepo.delete(o1Find);

		o1Find = ordinateurWithEmbeddedIdRepo.findById(o1.getId());

		Assert.assertNull(o1Find);
	
//		if(o1Find != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");
	
		int sizeStart = ordinateurWithEmbeddedIdRepo.findAll().size();

		OrdinateurWithEmbeddedId o1 = new OrdinateurWithEmbeddedId(8,true, "I7");
		
		o1 = ordinateurWithEmbeddedIdRepo.save(o1);
		
		OrdinateurWithEmbeddedId o2 = new OrdinateurWithEmbeddedId(16,false, "I23");

		o2 = ordinateurWithEmbeddedIdRepo.save(o2);

		int sizeEnd = ordinateurWithEmbeddedIdRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);
		
		System.out.println("testFindAll Fin ###################");
	}

}
