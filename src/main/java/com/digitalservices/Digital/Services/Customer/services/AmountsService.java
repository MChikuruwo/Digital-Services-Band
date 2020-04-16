package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.Amounts;

import java.util.List;

public interface AmountsService {
    String add(Amounts amounts);
    String update(Amounts amounts);
    String delete(Long id);
    List<Amounts> getAll();
    Amounts getOne(Long id);


    Amounts findByAmount(String amount);
}
