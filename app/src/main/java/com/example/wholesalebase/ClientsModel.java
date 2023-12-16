package com.example.wholesalebase;

public class ClientsModel
{

    String name, phone, adres;

    ClientsModel()
    {

    }

    public ClientsModel(String name, String phone, String adres) {
        this.name = name;
        this.phone = phone;
        this.adres = adres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
