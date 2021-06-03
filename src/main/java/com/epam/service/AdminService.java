package com.epam.service;

import com.epam.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {

    List<Client> getListClients() throws SQLException;

}
