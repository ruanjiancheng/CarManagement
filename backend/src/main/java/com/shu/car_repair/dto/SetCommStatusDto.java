package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-19
 * @description
 */
public class SetCommStatusDto {
    public Integer commissionID;

    public Integer status;

    public Integer getCommissionID() {
        return commissionID;
    }

    public void setCommissionID(Integer commissionID) {
        this.commissionID = commissionID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
