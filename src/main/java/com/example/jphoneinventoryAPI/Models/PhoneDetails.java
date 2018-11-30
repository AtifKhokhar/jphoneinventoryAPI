package com.example.jphoneinventoryAPI.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@ToString(exclude = {"id"})
@Document(collection = "PhoneDetails")
public class PhoneDetails {

    @Id
    private ObjectId Id;

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

    public PhoneDetails() {
    }
}
