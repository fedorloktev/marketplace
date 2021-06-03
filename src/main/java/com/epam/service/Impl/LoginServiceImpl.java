package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.model.Client;
import com.epam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private ClientDao clientDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl(ClientDao clientDao, PasswordEncoder passwordEncoder) {
        this.clientDao = clientDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String checkLoginUser(String login, String password) {
        Client foundClient = clientDao.getByLogin(login);
        if (foundClient == null || password == null || !passwordEncoder.matches(password, foundClient.getPassword())) {
            return null;
        }
        return foundClient.getFullName();
    }

}
