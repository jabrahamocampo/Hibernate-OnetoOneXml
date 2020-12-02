package com.jaom.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {

		try {
			// Create session factory from hibernate.cfg.xml file
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			System.out.println("############### Hibernate Configuration Loaded ###############");

			// ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
			// .applySettings(configuration.getProperties()).build();

			// SessionFactory sessionFactory =
			// configuration.buildSessionFactory(serviceRegistry);

			System.out.println("############### Hibernate SessionFactory created ###############");
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			return sessionFactory;

		} catch (Throwable ex) {
			System.err.println("############### Initial Session Factory creation failed. ###############" + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}
}
