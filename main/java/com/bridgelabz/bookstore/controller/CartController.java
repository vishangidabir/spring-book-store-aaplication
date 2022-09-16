package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartdetails")
public class CartController {

    @Autowired
    ICartService cartService;

    /**
     * Inserted cart details to database
     * this is POST call
     * http://localhost:8080/cartdetails/addtocart
     */
    @PostMapping("/addtocart")
    public ResponseEntity<ResposeDTO> addToCart(@RequestHeader(name = "Authorization") String token, @RequestBody CartDTO cartDTO) {
        ResposeDTO responseDTO = new ResposeDTO("Cart Added Successfully", cartService.addToCart(token, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieve all cart details to database
     * this is GET call
     * http://localhost:8080/cartdetails/getall
     */
    @GetMapping(value = {"/getall", "/"})
    public ResponseEntity<ResposeDTO> getAllCarts(@RequestHeader(name = "Authorization") String token) {
        ResposeDTO responseDTO = new ResposeDTO("Get call Successful", cartService.getAllCarts(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update cart details by id to database
     * this is GET call
     * http://localhost:8080/cartdetails/1
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<ResposeDTO> getCartById(@RequestHeader(name = "Authorization") String token, @PathVariable Long cartId) {
        ResposeDTO responseDTO = new ResposeDTO("Get call Successful", cartService.getCartById(token, cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update cart details by id to database
     * this is PUT call
     * http://localhost:8080/cartdetails/update/1
     */
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResposeDTO> updateCart(@RequestHeader(name = "Authorization") String token, @RequestBody CartDTO cartDTO, @PathVariable long cartId) {
        ResposeDTO responseDTO = new ResposeDTO("Cart Updated Successfully", cartService.UpdateCart(token, cartDTO, cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete cart details by id to database
     * this is PUT call
     * http://localhost:8080/cartdetails/delete/1
     */
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResposeDTO> deleteCartById(@RequestHeader(name = "Authorization") String token, @PathVariable Long cartId) {
        ResposeDTO responseDTO = new ResposeDTO("Cart Deleted Successfully", cartService.deleteCartById(token, cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
