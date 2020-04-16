package com.digitalservices.Digital.Services.Customer.services;

import com.digitalservices.Digital.Services.Customer.models.Bundles;

import java.util.List;

public interface BundlesService {
    String add(Bundles bundles);
    String update(Bundles bundles);
    String delete(Long id);
    List<Bundles> getAll();
    Bundles getOne(Long id);


    Bundles findByType(String bundleTypes);
}
