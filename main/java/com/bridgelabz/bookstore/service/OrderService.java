package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    //Ability to serve controller's insert order record api call
    public Order insertOrder(String token, OrderDTO orderDTO) {
        List<Long> bookIdList = orderDTO.getBookIdList();
        List<Long> quantityList = orderDTO.getQuantityList();
        long userId = tokenUtility.decodeToken(token);
        UserData user = userService.getRecord((int) userId).orElseThrow(() -> new CustomException("User id " + userId + " Not found!"));
        double totalPrice = 0;
        for (int i = 0; i < orderDTO.getBookIdList().size(); i++) {
            Book book = bookRepository.findBookById(bookIdList.get(i));
            if (quantityList.get(i) > book.getQuantity())
                throw new CustomException("Book quantity exceeded for book id " + bookIdList.get(i));
            else {
                totalPrice += book.getPrice() * quantityList.get(i);
            }
        }
        LocalDate purchaseDate = LocalDate.now();
        Order order = new Order(user, user.getAddress(), bookIdList, orderDTO.getQuantityList(), totalPrice, purchaseDate);
        Order newOrder = orderRepository.save(order);
        for (int i = 0; i < bookIdList.size(); i++) {
            long bookId = bookIdList.get(i);
            long orderQuantity = quantityList.get(i);
            Book book = bookRepository.findById((int) bookId).orElseThrow();
            bookService.updateQuantity(token, book.getBookID(), (int) (book.getQuantity() - orderQuantity));
        }
        emailService.sendEmail(user.getEmail(), "new Order Placed on BookSore", "Hello " + user.getFirstName() + user.getLastName() + ", Yous order of book id's " + bookIdList + " is placed successfully.");
        return newOrder;
    }

    //Ability to serve controller's Retrieve all record api call
    public List<Order> getAllOrderRecords(String token) {
        long userId = tokenUtility.decodeToken(token);
        UserData user = userService.getRecord((int) userId).orElseThrow(() -> new CustomException("User id " + userId + " Not found!"));
        if (!user.isAdmin()) throw new CustomException("User is not Admin");
        if (orderRepository.findAll().isEmpty()) throw new CustomException("User table is empty");
        else return orderRepository.findAll();
    }

    //Ability to serve controller's Retrieve record by id api call
    public Order getOrderRecord(String token, long orderId) {
        long userId = tokenUtility.decodeToken(token);
        return orderRepository.findById((int) orderId).orElseThrow(() -> new CustomException("order id " + orderId + " not found"));
    }

    //Ability to serve controller's Delete record by id api call
    public String deleteOrderById(String token, long orderId) {
        long userId = tokenUtility.decodeToken(token);
        UserData user = userService.getRecord((int) userId).orElseThrow(() -> new CustomException("User id " + userId + " Not found!"));
        orderRepository.deleteById((int) orderId);
        return "deleted order of id " + orderId + " by " + user.getFirstName();
    }

    //Ability to serve controller's Update record by api call
    public Order updateOrderById(String token, long orderId, OrderDTO orderDTO) {
        List<Long> bookIdList = orderDTO.getBookIdList();
        List<Long> quantityList = orderDTO.getQuantityList();
        long userId = tokenUtility.decodeToken(token);
        LocalDate purchaseDate = LocalDate.now();
        UserData user = userService.getRecord((int) userId).orElseThrow(() -> new CustomException("User of userId " + userId + " Not found!"));
        Order order = orderRepository.findById((int) orderId).orElseThrow(() -> new CustomException("Order od userId " + orderId + " Not found!"));
        double price = 0;
        for (int i = 0; i < orderDTO.getBookIdList().size(); i++) {
            Book book = bookRepository.findBookById(bookIdList.get(i));
            if (quantityList.get(i) > book.getQuantity())
                throw new CustomException("Book quantity exceeded for book id " + bookIdList.get(i));
            else {
                price += book.getPrice() * quantityList.get(i);
            }
        }
        order.setBookIdList(orderDTO.getBookIdList());
        order.setPrice(price);
        order.setPurchaseDate(purchaseDate);
        order.setUser(user);
        order.setAddress(user.getAddress());
        order.setQuantities(orderDTO.getQuantityList());
        Order updatedOrder = orderRepository.save(order);
        emailService.sendEmail(user.getEmail(), "Order updated on BookSore", "Hello " + user.getFirstName() + user.getLastName() + ", Yous order of book id " + orderDTO.getBookIdList() + " is updated successfully.");
        return updatedOrder;
    }
}





