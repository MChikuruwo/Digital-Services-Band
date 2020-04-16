package com.digitalservices.Digital.Services.Customer.dto;

public class UpdateAmountsDto {

    private Long id;
    private String amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
