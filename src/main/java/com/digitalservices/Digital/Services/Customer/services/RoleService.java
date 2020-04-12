package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getOne(Integer id);
    Role findByName(String name);
}
