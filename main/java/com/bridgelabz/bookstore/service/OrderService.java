package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    //Ability to serve controller's insert order record api call
    public Order insertOrder(String token, OrderDTO orderdto) {
        long userId = tokenUtility.decodeToken(token);
        Optional<Book> book = bookRepository.findById(orderdto.getBookID());
        Optional<UserData> user = userRepository.findById(orderdto.getUserID());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                Order newOrder = new Order(book.get().getPrice(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
                orderRepository.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
                book.get().setPrice(book.get().getPrice() - orderdto.getPrice());
                bookRepository.save(book.get());
                System.out.println("Order record inserted successfully");
                emailService.sendEmail(orderdto.getEmail(), "Your Order Placed successfully", "Hello, Your order for " + newOrder + "  is placed successfully on " + newOrder.getDate() + " and will be delivered to you shortly.");
                return newOrder;
            } else {
                throw new CustomException("Requested quantity is not available");
            }
        } else {
            throw new CustomException("Book or User doesn't exists");
        }
    }

    //Ability to serve controller's retrieve all order records api call
    public List<Order> getAllOrderRecords() {
        if (!orderRepository.findAll().isEmpty()) {
            return orderRepository.findAll();
        } else throw new CustomException("Books Table is Empty!");
    }

    //Ability to serve controller's retrieve order record by id api call
    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new CustomException("Order Record doesn't exists");
        } else {
            System.out.println("Order record retrieved successfully for id " + id);
            return order.get();
        }
    }

    //Ability to serve controller's update order record by id api call
    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepository.findById(id);
        Optional<Book> book = bookRepository.findById(dto.getBookID());
        Optional<UserData> user = userRepository.findById(dto.getUserID());
        if (order.isEmpty()) {
            throw new CustomException("Order Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() <= book.get().getQuantity()) {
                    Order newOrder = new Order(id, book.get().getPrice(), dto.getQuantity(), dto.getAddress(), book.get(), user.get(), dto.isCancel());
                    orderRepository.save(newOrder);
                    System.out.println("Order record updated successfully for id " + id);
                    book.get().setQuantity(book.get().getQuantity() - (dto.getQuantity() - order.get().getQuantity()));
                    bookRepository.save(book.get());
                    return newOrder;
                } else {
                    throw new CustomException("Requested quantity is not available");
                }
            } else {
                throw new CustomException("Book or User doesn't exists");

            }
        }
    }


}