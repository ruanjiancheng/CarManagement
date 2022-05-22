package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class MaterialDto {
    public Integer materialID;

    public String name;

    public Integer count;

    public Integer price;

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
