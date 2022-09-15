package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    //Ability to serve to controller's insert or add cart details api call
    public Cart addToCart(String token, CartDTO cartDTO) {
        Cart cart;
        List<Integer> bookIdList = cartDTO.getBookIdList();
        List<Integer> quantityList = cartDTO.getQuantity();
        long userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById((int) userId).orElseThrow(() -> new CustomException("User id " + userId + " not found"));

        for (int i = 0; i < bookIdList.size(); i++) {
            if (quantityList.get(i) > bookRepository.findBookById(bookIdList.get(i)).getQuantity())
                throw new CustomException("Book quantity exceeded for book id " + bookIdList.get(i));
        }
        if (cartRepository.existsById(userId)) {
            cart = cartRepository.findById(userId).orElseThrow(() -> new CustomException("User id " + userId + " not found"));
            cart.setUserData(userData);
            cart.setBookIdList(cartDTO.getBookIdList());
            cart.setQuantity(cartDTO.getQuantity());
            return cartRepository.save(cart);
        } else {
            cart = new Cart(userId, userData, cartDTO.getBookIdList(), cartDTO.getQuantity());
            try {

                return cartRepository.save(cart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Ability to serve to controller's retrieve all cart records api call
    public List<Cart> getAllCartRecords() {
        if (!cartRepository.findAll().isEmpty()) {
            List<Cart> cartList = cartRepository.findAll();
            return cartList;
        } else throw new CustomException("Cart Table is Empty!");

    }


    //Ability to serve to controller's retrieve cart record by id api call
    public Cart getCartRecord(long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new CustomException("Cart Record doesn't exists");
        } else {
            System.out.println("Cart record retrieved successfully for id " + id);
            return cart.get();
        }
    }

}


