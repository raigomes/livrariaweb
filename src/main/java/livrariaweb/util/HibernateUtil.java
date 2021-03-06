package livrariaweb.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration().
			configure("livrariaweb/util/hibernate.cfg.xml")
			.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}