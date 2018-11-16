package com.example.jphoneinventoryAPI.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhoneDetails {

    public String make;
    public String model;
    public int price;
    public int quantity;

    public PhoneDetails(String make, String model, int price, int quantity) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }
}
