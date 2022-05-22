package com.shu.car_repair.entity.sub;

import io.swagger.models.auth.In;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class MaterialItem {
    public Integer materialID;

    public Integer count;

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
