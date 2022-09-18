package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Order;

import java.util.List;

public interface IOrderService {
    Order insertOrder(String token, OrderDTO orderDTO);

    List<Order> getAllOrderRecords(String token);

    Order getOrderRecord(String token, long orderId);

    String deleteOrderById(String token, long orderId);

    Order updateOrderById(String token, long orderId, OrderDTO orderDTO);


}
