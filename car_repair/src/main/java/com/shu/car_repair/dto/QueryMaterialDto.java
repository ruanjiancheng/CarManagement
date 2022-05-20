package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-17
 * @description
 */
public class QueryMaterialDto {
    public Integer highPrice;
    public Integer lowPrice;

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }
}
