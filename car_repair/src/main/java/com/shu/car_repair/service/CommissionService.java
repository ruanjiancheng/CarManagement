package com.shu.car_repair.service;

import com.shu.car_repair.entity.Commission;
import com.shu.car_repair.entity.Order;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface CommissionService {
    List<Commission> getAllCommissions();

    int insertCommission(Commission commission);

    int deleteCommissionByID(Integer id);

    int updateCommission(Commission commission);

    Commission selectCommissionByID(Integer id);

    Order getOrderByCommID(Integer commissionID);

    int setCommStatus(Integer commissionID, Integer status);

    int getTotalCost(Integer commissionID);
}
