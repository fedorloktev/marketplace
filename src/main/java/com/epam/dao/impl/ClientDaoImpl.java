package com.epam.dao.impl;

import com.epam.dao.ClientDao;
import com.epam.model.Client;
import com.epam.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private static final String SELECT_ALL = "FROM Client";
    private static final String SELECT_FOR_ID = "FROM Client WHERE ID = :id";
    private static final String SELECT_FOR_LOGIN = "FROM Client WHERE login = :login";
    private static final String SELECT_FOR_NAME = "FROM Client WHERE fullname LIKE :name";

    @Override
    public void add(Client entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Client> getAll() {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clientList = session
                .createQuery(SELECT_ALL)
                .list();
        transaction.commit();
        session.close();
        return clientList;
    }

    @Override
    public Client getById(int id) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = (Client) session
                .createQuery(SELECT_FOR_ID)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
        session.close();
        return client;
    }

    @Override
    public List<Client> getByName(String name) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clientList = session
                .createQuery(SELECT_FOR_NAME)
                .setParameter("name", name)
                .list();
        transaction.commit();
        session.close();
        return clientList;
    }

    @Override
    public Client getByLogin(String login) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = (Client) session
                .createQuery(SELECT_FOR_LOGIN)
                .setParameter("login", login)
                .getResultList().stream().findFirst().orElse(null);
        transaction.commit();
        session.close();
        return client;
    }

    @Override
    public void update(Client entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Client entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entry);
        transaction.commit();
        session.close();
    }

}
