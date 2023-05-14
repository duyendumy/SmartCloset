package com.example.smartclosetclient.model;


public class Item {

    private Long id;


    private String imagePath;


    private Category category;

    private SubCategory subCategory;


    private Brand brand;


    private Season season;


    private Closet closet;


    private Float price;


    private String color;


    private String description;


    private int display;

    public Item() {
        super();
    }

    public Item(String imagePath, Category category, SubCategory subCategory, Brand brand, Season season, Closet closet,
                Float price, String color, String description, int display) {
        super();
        this.imagePath = imagePath;
        this.category = category;
        this.subCategory = subCategory;
        this.brand = brand;
        this.season = season;
        this.closet = closet;
        this.price = price;
        this.color = color;
        this.description = description;
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }


    public Closet getCloset() {
        return closet; }

    public void setCloset(Closet closet) {
        this.closet = closet; }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

}
