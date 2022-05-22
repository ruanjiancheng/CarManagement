package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-17
 * @description
 */
public class QueryCarDto {
    public String color;

    public String numberPlate;

    public String category;

    public String model;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
