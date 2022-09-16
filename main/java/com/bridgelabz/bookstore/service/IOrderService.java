package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Order;

import java.util.List;

public interface IOrderService {

    Order insertOrder(String token, OrderDTO orderdto);

    List<Order> getAllOrderRecords();

    Order getOrderRecord(Integer id);

    Order updateOrderRecord(Integer id, OrderDTO dto);
}
