package com.epam.dao;

import com.epam.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {

    void add(Role entry) throws SQLException;

    List<Role> getAll() throws SQLException;

    Role getById(int id) throws SQLException;

    void update(Role entry) throws SQLException;

    void remove(Role entry) throws SQLException;

}
