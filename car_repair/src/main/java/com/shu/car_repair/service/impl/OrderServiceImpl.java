package com.shu.car_repair.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shu.car_repair.dto.OrderDto;
import com.shu.car_repair.entity.Commission;
import com.shu.car_repair.entity.Order;
import com.shu.car_repair.entity.Repairment;
import com.shu.car_repair.entity.sub.RepairDetail;
import com.shu.car_repair.mapper.CommissionMapper;
import com.shu.car_repair.mapper.OrderMapper;
import com.shu.car_repair.service.OrderService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;

    @Resource
    CommissionMapper commissionMapper;

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int deleteOrderByID(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order selectOrderByID(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderDto getRepairDetailByID(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderDto dto = transferToDto(order);
//        List<Person> plist = gson.fromJson(listJson, new TypeToken<List<Person>>(){}.getType());
        return dto;
    }

    @Override
    public int insertDetail(Integer orderID, Integer detailID, String newDetail) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        OrderDto dto = transferToDto(order);
        List<RepairDetail> details = dto.getRepairDetail();
        List<RepairDetail> insertDetails = getDetails(newDetail);
        details.addAll(insertDetails);
        dto.setRepairDetail(details);
        Order newOrder = transferToEntity(dto);
        orderMapper.updateByPrimaryKeySelective(newOrder);
        return orderID;
    }


    @Override
    public int deleteDetail(Integer orderID, Integer detailID) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        OrderDto dto = transferToDto(order);
        List<RepairDetail> details = dto.getRepairDetail();
        List<RepairDetail> res = new ArrayList<>();
        for (RepairDetail d: details) {
            if (d.repairmentID.equals(detailID)) {
                continue;
            }
            res.add(d);
        }
        dto.setRepairDetail(res);
        Order newOrder = transferToEntity(dto);
        orderMapper.updateByPrimaryKeySelective(newOrder);
        return orderID;
    }

    @Override
    public int updateDetail(Integer orderID, Integer detailID, String newDetail) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        OrderDto dto = transferToDto(order);
        List<RepairDetail> details = dto.getRepairDetail();
        List<RepairDetail> res = new ArrayList<>();

        Gson gson = new Gson();
        RepairDetail tmp = gson.fromJson(newDetail, RepairDetail.class);

        for (RepairDetail d: details) {
            if (d.repairmentID.equals(detailID)) {
                d = tmp;
            }
            res.add(d);
        }
        dto.setRepairDetail(res);
        Order newOrder = transferToEntity(dto);
        orderMapper.updateByPrimaryKeySelective(newOrder);
        return orderID;
    }

    @Override
    public List<Order> getOrderByWorkerID(Integer workerID) {
        List<Order> orders = orderMapper.getAllOrders();
        List<Order> res = new ArrayList<>();
        for (Order order: orders) {
            List<RepairDetail> details = getDetails(order.getRepairDetail());
            for (RepairDetail detail: details) {
                if (detail.WorkerID.equals(workerID)) {
                    res.add(order);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public List<RepairDetail> getFinishedOrderByWorkerID(Integer workerID) {
        List<Order> orders = orderMapper.getAllOrders();
        List<RepairDetail> res = new ArrayList<>();
        for (Order order: orders) {
            if (order.getStatus() != 2) continue;
            String d = order.getRepairDetail();
            if (d == null) continue;
            List<RepairDetail> details = getDetails(d);
            for (RepairDetail detail: details) {
                if (detail == null || detail.getStatus() != 2) continue;
                if (workerID.equals(detail.WorkerID)) {
                    detail.setOrderID(order.getId());
                    res.add(detail);
                }
            }
        }
        return res;
    }

    @Override
    public List<RepairDetail> getUnfinishedOrderByWorkerID(Integer workerID) {
        List<Order> orders = orderMapper.getAllOrders();
        List<RepairDetail> res = new ArrayList<>();
        for (Order order: orders) {
            if (order.getStatus() == 2) continue;
            String d = order.getRepairDetail();
            if (d == null) continue;
            List<RepairDetail> details = getDetails(d);
            for (RepairDetail detail: details) {
                if (detail == null || detail.getStatus() != 1) continue;
                if (workerID.equals(detail.WorkerID)) {
                    detail.setOrderID(order.getId());
                    res.add(detail);
                }
            }
        }
        return res;
    }

    @Override
    public OrderDto finishOneRepair(Integer orderID, Integer detailID) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        List<RepairDetail> details = getDetails(order.getRepairDetail());
        boolean flag = true;
        List<RepairDetail> newDetails = new ArrayList<>();
        for (RepairDetail d: details) {
            RepairDetail cur = new RepairDetail();
            cur.setRepairmentID(d.repairmentID);
            cur.setStatus(d.status);
            cur.setWorkerID(d.WorkerID);
            cur.setWorkTime(d.workTime);
            if (detailID.equals(d.repairmentID)) {
                cur.setStatus(2);
            }
            if (!cur.getStatus().equals(2)) {
                flag = false;
            }
            newDetails.add(cur);
        }
        if (flag) {
            order.setStatus((byte)2);
            Commission commission = commissionMapper.selectByPrimaryKey(order.getCommissionId());
            commission.setStatus((byte)2);
            commissionMapper.updateByPrimaryKeySelective(commission);
        }
        order.setRepairDetail(getDetailsString(newDetails));
        orderMapper.updateByPrimaryKeySelective(order);
        return transferToDto(order);
    }

    @Override
    public Order addRepairment(Integer orderID, Integer detailID, Integer workerID, Integer workTime) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        if (order == null) return null;
        List<RepairDetail> details = getDetails(order.getRepairDetail());
        if (details == null) details = new ArrayList<>();
        RepairDetail detail = new RepairDetail();
        detail.setWorkerID(workerID);
        detail.setRepairmentID(detailID);
        detail.setWorkTime(workTime);
        detail.setStatus(1);
        details.add(detail);
        order.setRepairDetail(getDetailsString(details));
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    @Override
    public int updateOrderStatus(Integer orderID, Integer status) {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        if (order == null) return -1;
        int tmp = status;
        order.setStatus((byte)tmp);
        orderMapper.updateByPrimaryKeySelective(order);
        return orderID;
    }

    public OrderDto transferToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCommissionId(order.getCommissionId());
        dto.setOrderId(order.getOrderId());
        dto.setStatus(order.getStatus());

        List<RepairDetail> details = getDetails(order.getRepairDetail());
        dto.setRepairDetail(details);
        return dto;
    }

    public Order transferToEntity(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderId(dto.getOrderId());
        order.setCommissionId(dto.getCommissionId());
        order.setStatus(dto.getStatus());
        order.setRepairDetail(getDetailsString(dto.getRepairDetail()));
        return order;
    }

    public List<RepairDetail> getDetails(String detail) {
        Gson gson = new Gson();
        return gson.fromJson(detail, new TypeToken<List<RepairDetail>>(){}.getType());
    }

    public String getDetailsString(List<RepairDetail> details) {
        Gson gson = new Gson();
        return gson.toJson(details);
    }


}
