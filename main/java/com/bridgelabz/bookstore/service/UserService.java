package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    //Ability to serve controller's add user record api call
    public String addUser(UserDTO userdto) {
        UserData userData = new UserData(userdto);
        userRepository.save(userData);
        String token = tokenUtility.createToken(userData.getUserID());
        emailService.sendEmail(userdto.getEmail(), "Account Sign-up successfully", "Hello" + userData.getFirstName() + " Your Account has been created.Your token is " + token + " Keep this token safe to access your account in future ");
        return token;
    }

    //Ability to serve controller's retrieve all user records api call
    public List<UserData> getAllRecords() {
        if (!userRepository.findAll().isEmpty()) {
            return userRepository.findAll();
        } else throw new CustomException("Users Table is empty!");
    }

    //Ability to serve controller's retrieve  user records by id api call
    public Optional<UserData> getRecord(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id);
        } else throw new CustomException("User id " + id + " Not found!");
    }

    //Ability to serve controller's retrieve  user records by email api call
    public UserData getUserByEmailId(String email) {
        List<UserData> userData = userRepository.findByEmail(email);
        if (userData.isEmpty()) {
            throw new CustomException("User record does not exist");
        } else {
            return userData.get(Integer.parseInt(email));
        }
    }

    //Ability to serve controller's retrieve  user records by token api call
    public UserData getRecordByToken(String token) {
        int id = (int) tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(id);
        if (userData.isEmpty()) {
            throw new CustomException("User Record doesn't exists");
        } else {
            return userData.get();
        }
    }

    //Ability to serve controller's update user records by email id api call
//    public UserData updateUserByEmail(String email, UserDTO userDTO) {
//        if (userRepository.findByEmailId(email) != null) {
//            int userID = userRepository.findByEmailId(email).getUserID();
//            UserData userData = new UserData(userDTO);
//            userData.setUserID(userID);
//            return userRepository.save(userData);
//        } else throw new CustomException("User with email " + email + " is Not Found");
//    }

    //Ability to serve controller's user login records api call
    public String userLogin(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            UserData userData = (UserData) userRepository.findByEmail(email);
            if (userData.getPassword().equals(password)) {
                userData.setLogin(true);
                String token = tokenUtility.createToken(userData.getUserID());
                userRepository.save(userData);
                return "Login SuccessFull! token = " + token;
            } else return "Incorrect password";
        } else
            throw new CustomException("User with email " + email + " is Not Found");
    }

    //Ability to serve controller's change password records api call
    public String changePassword(String email, String token, String newPassword) {
        if (userRepository.findByEmail(email) != null) {
            UserData userData = (UserData) userRepository.findByEmail(email);
            long id = tokenUtility.decodeToken(token);
            if (userData.getUserID() == (id)) {
                userData.setPassword(newPassword);
                userRepository.save(userData);
                return "Password Changed SuccessFull";
            } else return "Incorrect token";
        } else throw new CustomException("User with email " + email + " is Not Found");
    }

}


