package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.service.CartService;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ResposeDTO> addToCart(@RequestHeader(name = "Authorization") String token, @RequestBody CartDTO cartDTO){
        ResposeDTO responseDTO = new ResposeDTO("Cart Added Successfully", cartService.addToCart(token, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieve all cart details to database
     * this is GET call
     * http://localhost:8080/cartdetails/retrieveAllCarts
     */
    @GetMapping("/retrieveAllCarts")
    public ResponseEntity<ResposeDTO> getAllCartRecords(){
        ResposeDTO dto = new ResposeDTO("All records retrieved successfully !",cartService.getAllCartRecords());
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Retrieve all cart details to database
     * this is GET call
     * http://localhost:8080/cartdetails/retrieveCart/1
     */
    @GetMapping("/retrieveCart/{id}")
    public ResponseEntity<ResposeDTO> getBookRecord(@PathVariable Integer id){
        ResposeDTO dto = new ResposeDTO("Record retrieved successfully !",cartService.getCartRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
//    //Ability to call api to update cart by id
//    @PutMapping("/updateCart/{id}")
//    public ResponseEntity<ResposeDTO> updateCartRecord(@PathVariable Integer id,@Valid @RequestBody CartDTO cartdto){
//        ResposeDTO dto = new ResposeDTO("Record updated successfully !",cartService.updateCartRecord(id,cartdto));
//        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
//    }
//    //Ability to call api to update quantity of book in cart by id
//    @PutMapping("/updateQuantity/{id}/{quantity}")
//    public ResponseEntity<ResposeDTO> updateQuantity(@PathVariable Integer id,@PathVariable Integer quantity){
//        ResposeDTO dto = new ResposeDTO("Quantity for book record updated successfully !",cartService.updateQuantity(id,quantity));
//        return new ResponseEntity(dto,HttpStatus.OK);
//    }
//    //Ability to call api to delete cart by id
//    @DeleteMapping("/deleteCart/{id}")
//    public ResponseEntity<ResposeDTO> deleteCartRecord(@PathVariable Integer id) {
//        ResposeDTO dto = new ResposeDTO("Record deleted successfully !", cartService.deleteCartRecord(id));
//        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
//    }
//    //Ability to call api to delete All cart
//    @DeleteMapping("/deleteall")
//    public ResponseEntity<ResposeDTO> deleteBooks() {
//        List<Cart> books = cartService.deleteAllFromCart();
//        return new ResponseEntity(books, HttpStatus.OK);
//    }

}
