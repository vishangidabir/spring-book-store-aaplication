package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderdetails")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * Retrieve all order details to database
     * this is POST call
     * http://localhost:8080/orderdetails/insert
     */
    @PostMapping("/insert")
    public ResponseEntity<ResposeDTO> placeOrder(@RequestHeader(name = "Authorization") String token, @RequestBody OrderDTO orderDTO) {
        ResposeDTO responseDTO = new ResposeDTO("Order Placed Successfully", orderService.insertOrder(token, orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieve all order details to database
     * this is GET call
     * http://localhost:8080/orderdetails/retrieveAllOrders
     */
    @GetMapping("/retrieveAllOrders")
    public ResponseEntity<ResposeDTO> getAllOrders(@RequestHeader(name = "Authorization") String token) {
        ResposeDTO responseDTO = new ResposeDTO("GET Call Success", orderService.getAllOrderRecords(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve order details to database
     * this is GET call
     * http://localhost:8080/orderdetails/retrieveOrder/1
     */
    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResposeDTO> getOrderById(@RequestHeader(name = "Authorization") String token, @PathVariable long orderId) {
        ResposeDTO responseDTO = new ResposeDTO("GET Call Success", orderService.getOrderRecord(token, orderId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete order details by id to database
     * this is DELETE call
     * http://localhost:8080/orderdetails/deleteOrder/1
     */
    @DeleteMapping("deleteOrder/{id}")
    public ResponseEntity<ResposeDTO> deleteOrderById(@RequestHeader(name = "Authorization") String token, @PathVariable long orderId) {
        ResposeDTO responseDTO = new ResposeDTO("GET Call Success", orderService.deleteOrderById(token, orderId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update order details by id to database
     * this is PUT call
     * http://localhost:8080/orderdetails/updateOrder/1
     */
    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<ResposeDTO> updateOrderById(@RequestHeader(name = "Authorization") String token, @PathVariable long orderId, @RequestBody OrderDTO orderDTO) {
        ResposeDTO responseDTO = new ResposeDTO("GET Call Success", orderService.updateOrderById(token, orderId, orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
