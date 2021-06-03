package com.epam.dao.impl;

import com.epam.dao.LotDao;
import com.epam.model.Client;
import com.epam.model.Lot;
import com.epam.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class LotDaoImpl implements LotDao {

    private static final String SELECT_ALL = "FROM Lot order by id";
    private static final String SELECT_FOR_ID = "FROM Lot WHERE id = :id";
    private static final String UPDATE_BEST_PRICE = "UPDATE Lot SET bestPrice =:newBestPrice, bidderId " +
            "=:newBidder WHERE id = :id";
    private static final String UPDATE_DATE = "UPDATE Lot SET stopDate = :newStopDate WHERE id = :id";

    @Override
    public void add(Lot entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Lot> getAll() {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Lot> lotList = session
                .createQuery(SELECT_ALL)
                .list();
        transaction.commit();
        session.close();
        return lotList;
    }

    @Override
    public Lot getById(int id) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        Lot lot = (Lot) session
                .createQuery(SELECT_FOR_ID)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
        session.close();
        return lot;
    }

    @Override
    public void updateBestPrice(int id, double newBestPrice, Client client) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery(UPDATE_BEST_PRICE)
                .setParameter("id", id)
                .setParameter("newBestPrice", newBestPrice)
                .setParameter("newBidder", client)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateDate(int id, ZonedDateTime newStopDate) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery(UPDATE_DATE)
                .setParameter("id", id)
                .setParameter("newStopDate", newStopDate)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Lot entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Lot entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entry);
        transaction.commit();
        session.close();
    }

}
