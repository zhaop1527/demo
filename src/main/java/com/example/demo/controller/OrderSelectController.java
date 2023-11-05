package com.example.demo.controller;

import com.example.demo.entity.OrderBean;
import com.example.demo.service.OrderSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class OrderSelectController {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrderSelectController.class, args);
        OrderSelectService orderSelectService = context.getBean(OrderSelectService.class);
        OrderBean orderBean = orderSelectService.selectOrder(1);
        System.out.println(orderBean);

    }


}
