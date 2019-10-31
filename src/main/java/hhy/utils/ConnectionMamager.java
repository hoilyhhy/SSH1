package hhy.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ConnectionMamager {

    private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration().configure("/hibernate.properties");
        sessionFactory = cfg.buildSessionFactory();


    }


    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
