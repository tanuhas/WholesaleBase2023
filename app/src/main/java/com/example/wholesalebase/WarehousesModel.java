package com.example.wholesalebase;

public class WarehousesModel {

    String name, adres;

    WarehousesModel() {

    }

    public WarehousesModel(String name, String adres) {
        this.name = name;
        this.adres = adres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}