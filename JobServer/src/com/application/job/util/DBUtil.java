package com.application.job.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;	

public class DBUtil {
	
	/*

	private static SessionFactory sessionFactory;
//	private static ServiceRegistry serviceRegistry;
	
	private static SessionFactory configureSessionFactory()
			throws HibernateException {
//		 Configuration configuration = new Configuration();
//		 configuration.configure();
//		 serviceRegistry = new ServiceRegistryBuilder().applySettings(
//		 configuration.getProperties()).buildServiceRegistry();
//		 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		 return sessionFactory;

		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return configureSessionFactory();

	}
	*/
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();

        } catch (HibernateException he) {
            System.out.println("Session Factory creation failure");
            throw he;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
