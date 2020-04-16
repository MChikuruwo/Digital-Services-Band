package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.Services;

import java.util.List;

public interface ServiceOfferedService {
    String add(Services services);
    String update(Services services);
    String delete(Long id);
    List<Services> getAll();
    Services getOne(Long id);


    Services findByType(String loanType);
}
