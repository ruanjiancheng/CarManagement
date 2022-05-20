package com.shu.car_repair.dto;

import com.shu.car_repair.entity.sub.MaterialItem;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class RepairmentDto {
    private Integer id;

    private Integer repairmentId;

    private String name;

    private List<MaterialItem> material;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepairmentId() {
        return repairmentId;
    }

    public void setRepairmentId(Integer repairmentId) {
        this.repairmentId = repairmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MaterialItem> getMaterial() {
        return material;
    }

    public void setMaterial(List<MaterialItem> material) {
        this.material = material;
    }
}
