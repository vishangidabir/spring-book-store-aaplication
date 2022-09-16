package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String addUser(UserDTO userDTO);

    List<UserData> getAllRecords();

    Optional<UserData> getRecord(int id);

    UserData getUserByEmailId(String email);

    UserData getRecordByToken(String token);

//    UserData updateUserByEmail(String email, UserDTO userDTO);

    String userLogin(String email, String password);

    String changePassword(String email, String token, String newPassword);
}
