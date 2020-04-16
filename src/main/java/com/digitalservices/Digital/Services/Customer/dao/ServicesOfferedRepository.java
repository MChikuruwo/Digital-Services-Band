package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesOfferedRepository extends JpaRepository<Services,Long> {

    Services findByServiceType(String type);

}
