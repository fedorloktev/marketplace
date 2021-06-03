package com.epam.dao;

import com.epam.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    void add(Client entry) throws SQLException;

    List<Client> getAll() throws SQLException;

    Client getById(int id) throws SQLException;

    List<Client> getByName(String name);

    Client getByLogin(String login);

    void update(Client entry) throws SQLException;

    void remove(Client entry) throws SQLException;

}
