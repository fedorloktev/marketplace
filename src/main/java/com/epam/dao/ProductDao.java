package com.epam.dao;

import com.epam.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    void add(Product entry) throws SQLException;

    List<Product> getAll() throws SQLException;

    Product getById(int id) throws SQLException;

    List<Product> getByName(String name);

    void update(Product entry) throws SQLException;

    void remove(Product entry) throws SQLException;

}
