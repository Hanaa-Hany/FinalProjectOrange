package com.hanaahany.finalprojectorange.model;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.RequestCreator;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductModelDetails implements Serializable {


    private int id;
    private double price;
    private String title;
    private String description;
    private String category;
    private double discountPercentage;
    @SerializedName("rating")
    private double rating;
    private int stock;
    private String brand;
    private String thumbnail;
    private ArrayList<String>images=new ArrayList<>();

    public ProductModelDetails() {

    }

    public ProductModelDetails(double price, String title, String description, String category) {
        this.price = price;
        this.title = title;
        this.description = description;
        this.category = category;

    }

    public ProductModelDetails(int id, double price, String title, String description, String category, double discountPercentage, double rating, int stock, String brand, String thumbnail, ArrayList<String> images) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.category = category;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.thumbnail = thumbnail;
        this.images = images;
    }



    public int getId() {
        return id;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }



    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", images=" + images +
                '}';
    }
}
