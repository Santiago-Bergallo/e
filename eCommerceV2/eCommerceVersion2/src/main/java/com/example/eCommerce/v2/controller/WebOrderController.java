package com.example.eCommerce.v2.controller;

import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.model.WebOrder;
import com.example.eCommerce.v2.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebOrderController {

    @Autowired
    WebOrderService webOrderService;

    @GetMapping("/weborders")
    public List<WebOrder> listOrdersByUser(@AuthenticationPrincipal LocalUser user) {
        return webOrderService.listWebOrders(user);
    }


}
