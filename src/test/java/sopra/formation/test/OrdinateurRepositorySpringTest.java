package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Ordinateur;
import sopra.formation.model.OrdinateurId;
import sopra.formation.repository.IOrdinateurRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class OrdinateurRepositorySpringTest {

	@Autowired
	private IOrdinateurRepository ordinateurRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Ordinateur o1 = new Ordinateur("AJC","166", 8,true, "I7");

		o1 = ordinateurRepo.save(o1);

		Ordinateur o1Find = ordinateurRepo.findById(new OrdinateurId(o1.getSociete(), o1.getCode()));

		Assert.assertEquals("AJC", o1Find.getSociete());
		Assert.assertEquals("166", o1Find.getCode());
		Assert.assertEquals(8, o1Find.getRam());
		Assert.assertEquals(true, o1Find.isSsd());
		Assert.assertEquals("I7", o1Find.getCpu());
		
		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Ordinateur o1 = new Ordinateur("AJC","166", 8,true, "I7");

		o1 = ordinateurRepo.save(o1);

		Ordinateur o1Find = ordinateurRepo.findById(new OrdinateurId(o1.getSociete(), o1.getCode()));

		o1Find.setSociete("AJC Ingenierie");
		o1Find.setCode("105");
		o1Find.setRam(16);
		o1Find.setSsd(false);
		o1Find.setCpu("I1");
		
		
		
		o1Find = ordinateurRepo.save(o1Find);

		o1Find = ordinateurRepo.findById(new OrdinateurId(o1Find.getSociete(), o1Find.getCode()));

		Assert.assertEquals("AJC Ingenierie", o1Find.getSociete());
		Assert.assertEquals("105", o1Find.getCode());
		Assert.assertEquals(16, o1Find.getRam());
		Assert.assertEquals(false, o1Find.isSsd());
		Assert.assertEquals("I1", o1Find.getCpu());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Ordinateur o1 = new Ordinateur("AJC","166", 8,true, "I7");

		o1 = ordinateurRepo.save(o1);

		Ordinateur o1Find = ordinateurRepo.findById(new OrdinateurId(o1.getSociete(), o1.getCode()));

		ordinateurRepo.delete(o1Find);

		o1Find = ordinateurRepo.findById(new OrdinateurId(o1.getSociete(), o1.getCode()));

		Assert.assertNull(o1Find);

//		if(o1Find != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = ordinateurRepo.findAll().size();

		Ordinateur o1 = new Ordinateur("AJC","166", 8,true, "I7");

		o1 = ordinateurRepo.save(o1);

		Ordinateur o2 = new Ordinateur("SOPRA","55", 16,false, "I23");

		o2 = ordinateurRepo.save(o2);

		int sizeEnd = ordinateurRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
