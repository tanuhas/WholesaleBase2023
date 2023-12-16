package com.example.wholesalebase;

public class OrdersModel {

    String articul, name, price, quantity, warehouse;

    OrdersModel() {

    }

    public OrdersModel(String articul, String name, String price, String quantity, String warehouse) {
        this.articul = articul;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
