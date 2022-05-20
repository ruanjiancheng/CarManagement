package com.shu.car_repair.entity;

public class Repairment {
    private Integer id;

    private Integer repairmentId;

    private String name;

    private String material;

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}