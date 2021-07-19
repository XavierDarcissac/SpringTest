package sopra.formation.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// On active les annotations @Transactional avec transactionManager
@EnableTransactionManagement
// Indiquer dans quel paquetage scanner pour trouver des annotations (activation implicite)
@ComponentScan("sopra.formation.repository.jpa")
// Chargement du fichier db.properties en mémoire
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

	@Autowired
	private Environment env;

//	On crée la dataSource via dbcp2
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setMaxTotal(Integer.valueOf(env.getProperty("db.maxTotal")));

		return dataSource;
	}

//	On crée un entityManagerFactory local à partir de la dataSource
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(BasicDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("sopra.formation.model");
//		On précise le provider (Hibernate) ...
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaProperties(this.hibernateProperties());

		return emf;
	}

//	On précise les propriétés du provider (Hibernate) ...
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", env.getProperty("db.dialect"));
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

//	On crée le transactionManagerpour JPA avec entityManagerFactory
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
//	On active la translation d'exception (Exception interne (Repo) => DataAccessException)
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
