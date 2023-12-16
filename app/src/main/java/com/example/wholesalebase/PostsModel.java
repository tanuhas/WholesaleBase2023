package com.example.wholesalebase;

public class PostsModel {

    String name, post, phone;

    PostsModel() {

    }

    public PostsModel(String name, String post, String phone) {
        this.name = name;
        this.post = post;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
