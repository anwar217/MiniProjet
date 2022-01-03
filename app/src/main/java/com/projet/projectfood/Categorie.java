package com.projet.projectfood;

import java.io.Serializable;

public class Categorie implements Serializable {
    private String Name;
    private String Image;
    private Double Price;
    private String Description;
    private  int Stars;
    private int Time;
    private int Calory;
    private int numberInCart;
    public Categorie(){}

    public Categorie(String name, String image, Double price, String description, int stars, int time, int calory, int numberInCart) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
        Stars = stars;
        Time = time;
        Calory = calory;
        this.numberInCart = numberInCart;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getStars() {
        return Stars;
    }

    public void setStars(int stars) {
        Stars = stars;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getCalory() {
        return Calory;
    }

    public void setCalory(int calory) {
        Calory = calory;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
