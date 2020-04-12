package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByMobileNumber(@Param("mobileNumber") String mobileNumber);
    List<User> findAllByActive(Boolean active);
}
