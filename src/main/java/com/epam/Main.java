package com.epam;

import com.epam.dao.LotDao;
import com.epam.dao.impl.LotDaoImpl;
import com.epam.model.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) throws SQLException {
    Main main = new Main();
    main.code();
    }

    void code() throws SQLException {

        LotDao lotDao = new LotDaoImpl();
        List<Lot> list = lotDao.getAll();
        for (Lot i : list) {
            System.out.println(i);
        }

    }


}