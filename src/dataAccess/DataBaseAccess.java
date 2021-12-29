package dataAccess;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseAccess {
       public static SessionFactory sessionFactory = new Configuration().configure("hibernateFile/hibernate.cfg.xml").buildSessionFactory();
    }

