package com.example.jphoneinventoryAPI.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhoneDetails {

    public String Make;
    public String Model;
    public int Price;
    public int Quantity;

    public PhoneDetails(String make, String model, int price, int quantity) {
        this.Make = make;
        this.Model = model;
        this.Price = price;
        this.Quantity = quantity;
    }
}
