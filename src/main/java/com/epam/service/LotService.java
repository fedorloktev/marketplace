package com.epam.service;

import com.epam.model.Lot;

import java.sql.SQLException;

public interface LotService {

    void updateLots(Lot lot);

    void addLot(Lot lot) throws SQLException;

}
