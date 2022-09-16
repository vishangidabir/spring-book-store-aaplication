package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderdetails")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * Inserted order details to database
     * this is POST call
     * http://localhost:8080/orderdetails/insert
     */
    //Ability to call api to insert order record
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
    public ResponseEntity<ResposeDTO> getAllOrderRecords() {
        ResposeDTO dto = new ResposeDTO("All records retrieved successfully !", orderService.getAllOrderRecords());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Retrieve order details to database
     * this is GET call
     * http://localhost:8080/orderdetails/retrieveOrder/1
     */
    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResposeDTO> getBookRecord(@PathVariable Integer id) {
        ResposeDTO dto = new ResposeDTO("Record retrieved successfully !", orderService.getOrderRecord(id));
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Update order details by id to database
     * this is PUT call
     * http://localhost:8080/orderdetails/updateOrder/1
     */
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<ResposeDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody OrderDTO orderdto) {
        ResposeDTO dto = new ResposeDTO("Record updated successfully !", orderService.updateOrderRecord(id, orderdto));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

}
