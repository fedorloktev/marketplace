package com.epam.controller;

import com.epam.mapper.ModelMapper;
import com.epam.model.LotDto;
import com.epam.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class LotController {

    private LotService lotService;
    private ModelMapper modelMapper;

    @Autowired
    public LotController(LotService lotService, ModelMapper modelMapper) {
        this.lotService = lotService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/update-lot")
    public void updateLots(@Valid @RequestBody LotDto lotDto) {
        lotService.updateLots(modelMapper.toLot(lotDto));
    }

    @PostMapping("/add-lot")
    public void addLot(@Valid @RequestBody LotDto lotDto) throws SQLException {
        lotService.addLot(modelMapper.toLot(lotDto));
    }

}
