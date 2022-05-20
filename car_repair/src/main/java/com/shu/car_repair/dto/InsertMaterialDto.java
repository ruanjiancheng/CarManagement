package com.shu.car_repair.dto;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class InsertMaterialDto {
    public Integer repairmentID;

    public Integer materialID;

    public Integer count;

    public Integer getRepairmentID() {
        return repairmentID;
    }

    public void setRepairmentID(Integer repairmentID) {
        this.repairmentID = repairmentID;
    }

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
