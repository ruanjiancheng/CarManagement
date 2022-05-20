package com.shu.car_repair.dto;

import com.shu.car_repair.entity.sub.RepairDetail;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-18
 * @description
 */
public class OrderDto {
    private Integer id;

    private Long orderId;

    private Integer commissionId;

    private Byte status;

    private List<RepairDetail> repairDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<RepairDetail> getRepairDetail() {
        return repairDetail;
    }

    public void setRepairDetail(List<RepairDetail> repairDetail) {
        this.repairDetail = repairDetail;
    }
}
