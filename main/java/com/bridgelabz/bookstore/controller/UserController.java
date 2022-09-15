package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/userdetails")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * Create and add user details to database
     * this is POST call
     * http://localhost:8080/userdetails/register
     */
    @PostMapping("/register")
    public ResponseEntity<ResposeDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        ResposeDTO responseDTO = new ResposeDTO("Added Successfully", userService.addUser(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieve all user to database
     * this is POST call
     * http://localhost:8080/userdetails/retrieveAll
     */
    @GetMapping("/retrieveAll")
    public ResponseEntity<ResposeDTO> getAllRecords() {
        ResposeDTO dto = new ResposeDTO("All records retrieved successfully !", userService.getAllRecords());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Retrieve user details by id to database
     * this is POST call
     * http://localhost:8080/userdetails/retrieve/1
     */
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ResposeDTO> getRecord(@PathVariable int id){
        ResposeDTO dto = new ResposeDTO("Record retrieved successfully !",userService.getRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Retrieve user details by email to database
     * this is POST call
     * http://localhost:8080/userdetails/retrieveByEmail/jitudabir@gmail.com
     */
    @GetMapping("/retrieveByEmail/{email}")
    public ResponseEntity<ResposeDTO> getUserByEmailId(@PathVariable String email) {
        ResposeDTO responseDTO = new ResposeDTO("Record for email successfully", userService.getUserByEmailId(email));
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve user details by token to database
     * this is POST call
     * http://localhost:8080/userdetails/retrieveByToken/token
     */
    @GetMapping("/retrieveByToken/{token}")
    public ResponseEntity<ResposeDTO> getRecordByToken(@PathVariable String token){
        ResposeDTO dto = new ResposeDTO("Record retrieved successfully !",userService.getRecordByToken(token));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * Update user details by email to database
     * this is POST call
     * http://localhost:8080/userdetails/update
     */
    @PutMapping("/update")
    public ResponseEntity<ResposeDTO> updateUserByEmail(@RequestParam String email, @RequestBody UserDTO userDTO ){
        ResposeDTO responseDTO = new ResposeDTO("User Updated Successfully", userService.updateUserByEmail(email, userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Login user id to database
     * this is POST call
     * http://localhost:8080/userdetails/login
     */
    @PostMapping("/login")
    public ResponseEntity<ResposeDTO> login(@RequestParam String email, String password){
        ResposeDTO responseDTO = new ResposeDTO("User Registration Successful!", userService.userLogin(email, password));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    /**
     * Change User password to database
     * this is POST call
     * http://localhost:8080/userdetails/change-password
     */
    @PostMapping("/change-password")
    public ResponseEntity<ResposeDTO> changePassword(@RequestParam String email, String token, String newPassword){
        ResposeDTO responseDTO = new ResposeDTO("User Registration Successful!", userService.changePassword(email, token, newPassword));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
