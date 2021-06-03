package com.epam.service;

import com.epam.model.Client;
import com.epam.model.Lot;
import com.epam.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface AuctionService {

    Lot updateBestBid(Lot lot);

    List<Lot> getListLots() throws SQLException;

    Product fillProductForLot(Lot lot, Client prodClient);

}
