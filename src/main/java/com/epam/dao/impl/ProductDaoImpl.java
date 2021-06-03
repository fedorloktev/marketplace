package com.epam.dao.impl;

import com.epam.dao.ProductDao;
import com.epam.model.Product;
import com.epam.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static final String SELECT_ALL = "FROM Product";
    private static final String SELECT_FOR_ID = "FROM Product WHERE id = :id";
    private static final String SELECT_FOR_NAME = "FROM Product p WHERE p.name LIKE :name";

    @Override
    public void add(Product entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> getAll() {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> productList = session
                .createQuery(SELECT_ALL)
                .list();
        transaction.commit();
        session.close();
        return productList;
    }

    @Override
    public Product getById(int id) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = (Product) session
                .createQuery(SELECT_FOR_ID)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> getByName(String name) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> prodList = session
                .createQuery(SELECT_FOR_NAME)
                .setParameter("name", name)
                .list();
        transaction.commit();
        session.close();
        return prodList;
    }

    @Override
    public void update(Product entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Product entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entry);
        transaction.commit();
        session.close();
    }

}
