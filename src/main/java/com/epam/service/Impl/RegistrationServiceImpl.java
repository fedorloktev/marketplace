package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.dao.impl.ClientDaoImpl;
import com.epam.model.Client;
import com.epam.model.Role;
import com.epam.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final int USER_ROLE_ID = 2;

    private ClientDao clientDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationServiceImpl(ClientDao clientDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientDao = clientDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean addUser(Client client) throws SQLException {
        Client clientFromDB = clientDao.getByLogin(client.getLogin());
        if (clientFromDB != null) {
            return false;
        }
        Role role = new Role();
        role.setId(USER_ROLE_ID);
        client.setRole(role);
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientDao.add(client);
        return true;
    }

}
