package com.epam.controller;

import com.epam.mapper.ModelMapper;
import com.epam.model.Lot;
import com.epam.model.LotDto;
import com.epam.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuctionController {

    private AuctionService auctionService;
    private ModelMapper modelMapper;

    @Autowired
    public AuctionController(AuctionService auctionService, ModelMapper modelMapper) {
        this.auctionService = auctionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/update-bid")
    public LotDto updateBid(@Valid @RequestBody LotDto lotDto) {
        Lot lot = modelMapper.toLot(lotDto);
        return modelMapper.toLotDto(auctionService.updateBestBid(lot));
    }

    @GetMapping(value = "/get-all-lots")
    public List<LotDto> getAllLots() throws SQLException {
        List<Lot> lots = auctionService.getListLots();
        return lots.stream()
                .map(modelMapper::toLotDto)
                .collect(Collectors.toList());
    }

}
