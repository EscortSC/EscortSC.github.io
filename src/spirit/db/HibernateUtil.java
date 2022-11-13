package spirit.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return (new Configuration()).configure().buildSessionFactory();
		} catch (Throwable arg0) {
			System.err
					.println("Initial SessionFactory creation failed." + arg0);
			throw new ExceptionInInitializerError(arg0);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
