package com.example.demo.dao;

import com.example.demo.entity.OrderBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestSelect {
//根据订单ID查询订单信息
    public abstract OrderBean selectOrder(int orderID);
}
