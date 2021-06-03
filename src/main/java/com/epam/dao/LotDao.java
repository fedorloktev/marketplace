package com.epam.dao;

import com.epam.model.Client;
import com.epam.model.Lot;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

public interface LotDao {

    void add(Lot entry) throws SQLException;

    List<Lot> getAll() throws SQLException;

    Lot getById(int id) throws SQLException;

    void update(Lot entry) throws SQLException;

    void updateBestPrice(int id, double newBestPrice, Client client);

    void updateDate(int id, ZonedDateTime newStopDate);

    void remove(Lot entry) throws SQLException;

}
