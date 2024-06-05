package com.example.stage6.User.Service;

import com.example.stage6.User.models.User;

import java.util.List;

public interface IUserService {


     List<User> getAllUsers();

     public boolean deleteUser(String id);


     User retrieveUser(String id);
     User updateUser(String id, User user);
}