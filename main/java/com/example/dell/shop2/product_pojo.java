package com.example.dell.shop2;


public class product_pojo {

    int id;
    String name;
    double price;
    String unit;
    double quantity;
    String description;

    public product_pojo(){}

    public product_pojo(int id, String name,double price, String unit,double quantity,String description){
        this.id = id;
        this.name = name;
        this.price=price;
        this.unit=unit;
        this.quantity=quantity;
        this.description=description;
    }

    public product_pojo(String name,double price, String unit,double quantity,String description){
        this.name = name;
        this.price=price;
        this.unit=unit;
        this.quantity=quantity;
        this.description=description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
