package sopra.formation.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

public class TestSpringConfXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IMatiereRepository matiereRepo = context.getBean(IMatiereRepository.class);

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		context.close();
	}

}
