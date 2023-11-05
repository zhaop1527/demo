package com.example.demo.service;


import com.example.demo.dao.TestSelect;
import com.example.demo.entity.OrderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@Service
public class OrderSelectService {
    @Autowired
    public TestSelect testSelect;
    //查询订单信息
    public OrderBean selectOrder(int orderID){
        OrderBean orderBean = testSelect.selectOrder(orderID);
        return orderBean;
    }
}
