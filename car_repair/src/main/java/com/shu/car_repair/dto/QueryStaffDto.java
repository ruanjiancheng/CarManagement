package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-16
 * @description
 */
public class QueryStaffDto {
    public String name;

    public Integer lowPrice;

    public Integer highPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }
}
