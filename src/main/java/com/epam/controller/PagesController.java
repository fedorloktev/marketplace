package com.epam.controller;

import com.epam.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/")
    public String callMainPage() {
        return "login.html";
    }

    @RequestMapping("/style.css")
    public String callStyle() {
        return "style.css";
    }

    @RequestMapping("/scriptAuction.js")
    public String callAuctionScript() {
        return "scriptAuction.js";
    }

    @RequestMapping("/scriptAdmin.js")
    public String callAdminScript() {
        return "scriptAdmin.js";
    }

    @RequestMapping("/scriptLogin.js")
    public String callLoginScript() {
        return "scriptLogin.js";
    }

    @RequestMapping("/scriptRegistration.js")
    public String callRegistrationScript() {
        return "scriptRegistration.js";
    }

    @RequestMapping("/scriptLot.js")
    public String callLotScript() {
        return "scriptLot.js";
    }

    @RequestMapping("/login.html")
    public String callLoginPage() {
        return "login.html";
    }

    @RequestMapping("/auction.html")
    public String callAuctionPage(Client client) {
        return "auction.html";
    }

    @RequestMapping("/registration.html")
    public String callRegistrationPage() {
        return "registration.html";
    }

    @RequestMapping("/addLot.html")
    public String callAddLotPage() {
        return "addLot.html";
    }

    @RequestMapping("/admin.html")
    public String callAdminPage() {
        return "admin.html";
    }

}
