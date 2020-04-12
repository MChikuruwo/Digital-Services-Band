package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.Login;
import com.digitalservices.Digital.Services.Customer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
