package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.dao.LotDao;
import com.epam.dao.ProductDao;
import com.epam.model.Client;
import com.epam.model.Lot;
import com.epam.model.Product;
import com.epam.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LotServiceImpl implements LotService {

    private ClientDao clientDao;
    private LotDao lotDao;
    private ProductDao productDao;

    @Autowired
    public LotServiceImpl(ClientDao clientDao, LotDao lotDao, ProductDao productDao) {
        this.clientDao = clientDao;
        this.lotDao = lotDao;
        this.productDao = productDao;
    }

    @Override
    public void updateLots(Lot lot) {
        lotDao.updateDate(lot.getId(), lot.getStopDate());
    }

    @Override
    public void addLot(Lot lot) throws SQLException {
        Client client = clientDao.getByName(lot.getProductId().getUserId().getFullName()).stream().findFirst().get();
        Product product = new Product();
        product.setName(lot.getProductId().getName());
        product.setDescription(lot.getProductId().getDescription());
        product.setUserId(client);
        productDao.add(product);
        Lot newLot = new Lot();
        newLot.setProductId(product);
        newLot.setStartPrice(lot.getStartPrice());
        newLot.setBidMin(lot.getBidMin());
        newLot.setStopDate(lot.getStopDate());
        lotDao.add(newLot);
    }

}
