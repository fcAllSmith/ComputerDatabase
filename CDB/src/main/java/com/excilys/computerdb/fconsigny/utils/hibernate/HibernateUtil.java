package com.excilys.computerdb.fconsigny.utils.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try
    {
      if (sessionFactory == null)
      {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
      }
      return sessionFactory;
    } catch (Throwable ex)
    {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }

}
