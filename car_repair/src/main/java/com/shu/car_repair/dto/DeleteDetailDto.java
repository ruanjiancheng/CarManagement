package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class DeleteDetailDto {
    public Integer orderID;

    public Integer detailID;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getDetailID() {
        return detailID;
    }

    public void setDetailID(Integer detailID) {
        this.detailID = detailID;
    }
}
