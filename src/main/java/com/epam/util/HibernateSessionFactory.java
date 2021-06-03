package com.epam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static volatile SessionFactory instance;

    public static SessionFactory getInstance() {
        SessionFactory result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SessionFactory.class) {
            if (instance == null) {
                instance = new Configuration().configure().buildSessionFactory();
            }
            return instance;
        }
    }

}
