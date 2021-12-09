package AE5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Model_AE5 {


	/*
	 * - Mètode: crearSessio
	 * - Descripció: Crea un SessionFactory amb el nostre archiu hibernate.cfg.xml. 
	 * - Paràmeters d'entrada: Ningún parámeter d'entrada
	 * - Paràmeters d'eixida: SessionFactory sessionFactory.
	 * 
	 */
	
	public static SessionFactory crearSessio() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		return sessionFactory;
	}

}
