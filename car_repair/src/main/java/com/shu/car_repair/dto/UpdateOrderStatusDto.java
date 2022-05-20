package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-19
 * @description
 */
public class UpdateOrderStatusDto {
    public Integer orderID;

    public Integer status;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
