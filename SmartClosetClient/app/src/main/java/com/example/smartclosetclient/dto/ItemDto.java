package com.example.smartclosetclient.dto;

public class ItemDto {
    private String imagePath;
    private Long categoryId;
    private Long subCategoryId;
    private String brandName;
    private Long seasonId;
    private Long closetId;
    private Float price;
    private String description;
    private String color;
    private int display;




    public ItemDto() {
    }

    public ItemDto(String imagePath, Long categoryId, Long subCategoryId, String brandName, Long seasonId, Long closetId,
                   Float price, String description, String color, int display) {
        super();
        this.imagePath = imagePath;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.brandName = brandName;
        this.seasonId = seasonId;
        this.closetId = closetId;
        this.price = price;
        this.description = description;
        this.color = color;
        this.display = display;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getSubCategoryId() {
        return subCategoryId;
    }
    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public Long getSeasonId() {
        return seasonId;
    }
    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }
    public Long getClosetId() {
        return closetId;
    }
    public void setClosetId(Long closetId) {
        this.closetId = closetId;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }


    public int getDisplay() {
        return display;
    }
    public void setDisplay(int display) {
        this.display = display;
    }

}
