package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.dao.LotDao;
import com.epam.model.Client;
import com.epam.model.Lot;
import com.epam.model.Product;
import com.epam.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    private ClientDao clientDao;
    private LotDao lotDao;

    @Autowired
    public AuctionServiceImpl(ClientDao clientDao, LotDao lotDao) {
        this.clientDao = clientDao;
        this.lotDao = lotDao;
    }

    @Override
    public Lot updateBestBid(Lot lot) {
        if (lot == null || lot.getBidderId() == null) {
            return null;
        }
        Client client = clientDao.getByName(lot.getBidderId().getFullName()).stream().findFirst().get();
        lotDao.updateBestPrice(lot.getId(), lot.getBestPrice(), client);
        return lot;
    }

    @Override
    public List<Lot> getListLots() throws SQLException {
        return lotDao.getAll();
    }

    @Override
    public Product fillProductForLot(Lot lot, Client prodClient) {
        Product product = new Product();
        product.setName(lot.getProductId().getName());
        product.setDescription(lot.getProductId().getDescription());
        product.setUserId(prodClient);
        return product;
    }

}
