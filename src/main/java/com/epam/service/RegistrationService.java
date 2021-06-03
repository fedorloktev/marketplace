package com.epam.service;

import com.epam.model.Client;

import java.sql.SQLException;

public interface RegistrationService {

    boolean addUser(Client client) throws SQLException;

}
