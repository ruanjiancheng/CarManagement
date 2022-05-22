package com.shu.car_repair.service;

import com.shu.car_repair.dto.OrderDto;
import com.shu.car_repair.entity.Order;
import com.shu.car_repair.entity.Repairment;
import com.shu.car_repair.entity.sub.RepairDetail;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface OrderService {
    List<Order> getAllOrders();

    int insertOrder(Order order);

    int deleteOrderByID(Integer id);

    int updateOrder(Order order);

    Order selectOrderByID(Integer id);

    OrderDto getRepairDetailByID(Integer id);

    int insertDetail(Integer orderID, Integer detailID, String newDetail);

    int deleteDetail(Integer orderID, Integer detailID);

    int updateDetail(Integer orderID, Integer DetailID, String newDetail);

    List<Order> getOrderByWorkerID(Integer workerID);

    List<RepairDetail> getFinishedOrderByWorkerID(Integer workerID);

    List<RepairDetail> getUnfinishedOrderByWorkerID(Integer workerID);

    OrderDto finishOneRepair(Integer orderID, Integer detailID);

    Order addRepairment(Integer orderID, Integer detailID, Integer workerID, Integer workTime);

    int updateOrderStatus(Integer orderID, Integer status);
}
