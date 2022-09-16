package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {
    Cart addToCart(String token, CartDTO cartDTO);

    List<Cart> getAllCarts(String token);

    Cart getCartById(String token, long id);

    String deleteCartById(String token, long id);

    Cart UpdateCart(String token, CartDTO cartDTO, long cartId);


}
