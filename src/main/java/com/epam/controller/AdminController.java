package com.epam.controller;

import com.epam.mapper.ModelMapper;
import com.epam.model.Client;
import com.epam.model.ClientDto;
import com.epam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    private AdminService adminService;
    private ModelMapper modelMapper;

    @Autowired
    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/get-all-clients")
    public List<ClientDto> getAllLots() throws SQLException {
        List<Client> clients = adminService.getListClients();
        return clients.stream()
                .map(modelMapper::toClientDto)
                .collect(Collectors.toList());
    }

}
