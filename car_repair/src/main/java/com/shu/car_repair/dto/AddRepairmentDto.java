package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-19
 * @description
 */
public class AddRepairmentDto {
    public Integer orderID;

    public Integer detailID;

    public Integer workerID;

    public Integer workTime;

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

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }
}
