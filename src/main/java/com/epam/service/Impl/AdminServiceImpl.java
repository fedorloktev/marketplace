package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.model.Client;
import com.epam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private ClientDao clientDao;

    @Autowired
    public AdminServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> getListClients() throws SQLException {
        List<Client> clients = clientDao.getAll();
        List<Client> returnClients = new ArrayList<>();
        for (Client client : clients) {
            client.setPassword(null);
            returnClients.add(client);
        }
        return returnClients;
    }

}
