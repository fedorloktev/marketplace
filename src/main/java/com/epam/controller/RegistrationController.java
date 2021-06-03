package com.epam.controller;

import com.epam.mapper.ModelMapper;
import com.epam.model.ClientDto;
import com.epam.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;

    @Autowired
    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-new-user")
    public boolean addUser(@Valid @RequestBody ClientDto clientDto) throws SQLException {
        return registrationService.addUser(modelMapper.toClient(clientDto));
    }

}
