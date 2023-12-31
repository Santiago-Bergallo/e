package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.model.WebOrder;
import com.example.eCommerce.v2.repository.WebOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
WebOrderDao webOrderDao;

    public List<WebOrder> listWebOrders(LocalUser user) {
       return webOrderDao.findByUser(user);
    }


}
