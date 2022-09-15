package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {
    public Cart addToCart(String token, CartDTO cartDTO);

    public List<Cart> getAllCartRecords();

    public Cart getCartRecord(long id);
}
