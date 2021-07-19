package sopra.formation.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;

public class TestSpringConfJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		IMatiereRepository matiereRepo = context.getBean(IMatiereRepository.class);

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

		
		
		
		context.close();
	}

}
