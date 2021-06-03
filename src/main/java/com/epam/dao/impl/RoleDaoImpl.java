package com.epam.dao.impl;

import com.epam.dao.RoleDao;
import com.epam.model.Role;
import com.epam.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final String SELECT_ALL = "FROM Role";
    private static final String SELECT_FOR_ID = "FROM Role WHERE id = :id";

    @Override
    public void add(Role entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Role> getAll() {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        List<Role> roleList = session
                .createQuery(SELECT_ALL)
                .list();
        transaction.commit();
        session.close();
        return roleList;
    }

    @Override
    public Role getById(int id) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        Role role = (Role) session
                .createQuery(SELECT_FOR_ID)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
        session.close();
        return role;
    }

    @Override
    public void update(Role entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entry);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Role entry) {
        Session session = HibernateSessionFactory.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entry);
        transaction.commit();
        session.close();
    }

}
