package com.shu.car_repair.entity.sub;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class RepairDetail {
    public Integer repairmentID;

    public Integer workTime;

    public Integer WorkerID;

    public Integer status;

    public Integer OrderID;

    public Integer getRepairmentID() {
        return repairmentID;
    }

    public void setRepairmentID(Integer repairmentID) {
        this.repairmentID = repairmentID;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public Integer getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(Integer workerID) {
        WorkerID = workerID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }
}
