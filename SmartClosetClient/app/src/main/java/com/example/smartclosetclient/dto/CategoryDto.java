package com.example.smartclosetclient.dto;

public class CategoryDto {
    private String name;

    private int display;

    private String imagePath;

    public CategoryDto() {
        super();
    }

    public CategoryDto(String name) {
        super();
        this.name = name;
    }

    public CategoryDto(int display) {
        super();
        this.display = display;
    }

    public CategoryDto(String name, int display, String imagePath) {
        super();
        this.name = name;
        this.display = display;
        this.imagePath = imagePath;
    }



    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
