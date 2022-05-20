package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-17
 * @description
 */
public class QueryClientDto {
    public String name;

    public Integer lowCarCount;

    public Integer highCarCount;

    public Integer highDiscountRate;

    public Integer lowDiscountRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLowCarCount() {
        return lowCarCount;
    }

    public void setLowCarCount(Integer lowCarCount) {
        this.lowCarCount = lowCarCount;
    }

    public Integer getHighCarCount() {
        return highCarCount;
    }

    public void setHighCarCount(Integer highCarCount) {
        this.highCarCount = highCarCount;
    }

    public Integer getHighDiscountRate() {
        return highDiscountRate;
    }

    public void setHighDiscountRate(Integer highDiscountRate) {
        this.highDiscountRate = highDiscountRate;
    }

    public Integer getLowDiscountRate() {
        return lowDiscountRate;
    }

    public void setLowDiscountRate(Integer lowDiscountRate) {
        this.lowDiscountRate = lowDiscountRate;
    }
}
