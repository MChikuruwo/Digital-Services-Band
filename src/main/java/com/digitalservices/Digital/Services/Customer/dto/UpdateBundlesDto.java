package com.digitalservices.Digital.Services.Customer.dto;

public class UpdateBundlesDto {

    private Long id;
    private String bundleTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBundleTypes() {
        return bundleTypes;
    }

    public void setBundleTypes(String bundleTypes) {
        this.bundleTypes = bundleTypes;
    }
}
