package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.User;

import java.util.List;

public interface UserService {

    String add(User user);

    String update(User user);

    String delete(Integer id);

    List<User> getAll();

    User getOne(Integer id);

    User authUser(String mobileNumber, String otp) throws Exception;

    User findByMobileNumber(String mobileNumber);
}
