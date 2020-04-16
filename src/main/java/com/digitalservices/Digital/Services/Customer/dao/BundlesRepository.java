package com.digitalservices.Digital.Services.Customer.dao;

import com.digitalservices.Digital.Services.Customer.models.Bundles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundlesRepository extends JpaRepository<Bundles,Long> {
    Bundles findByType(String type);
}
