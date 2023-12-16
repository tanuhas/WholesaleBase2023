package com.example.wholesalebase;

public class ProductsModel {

    String articul, name, price, warehouse, quantity;

    ProductsModel()
    {

    }
    public ProductsModel(String articul, String name, String price, String warehouse, String quantity) {
        this.articul = articul;
        this.name = name;
        this.price = price;
        this.warehouse = warehouse;
        this.quantity = quantity;
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

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
