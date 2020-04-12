package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
