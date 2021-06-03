package com.epam.controller;

import com.epam.mapper.ModelMapper;
import com.epam.model.ClientDto;
import com.epam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService, ModelMapper modelMapper) {
        this.loginService = loginService;
    }

    @PostMapping("/login-user")
    public String loginUser(@Valid @RequestBody ClientDto clientDto) {
        return loginService.checkLoginUser(clientDto.getLogin(), clientDto.getPassword());
    }

}
