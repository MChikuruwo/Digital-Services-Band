package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.Amounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmountsRepository extends JpaRepository<Amounts,Long> {
    Amounts findByAmounts(String amounts);
}
