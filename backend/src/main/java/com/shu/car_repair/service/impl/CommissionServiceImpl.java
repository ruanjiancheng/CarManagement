package com.shu.car_repair.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shu.car_repair.entity.*;
import com.shu.car_repair.entity.sub.MaterialItem;
import com.shu.car_repair.entity.sub.RepairDetail;
import com.shu.car_repair.mapper.*;
import com.shu.car_repair.service.CommissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class CommissionServiceImpl implements CommissionService {
    @Resource
    CommissionMapper commissionMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    RepairmentMapper repairmentMapper;

    @Resource
    MaterialMapper materialMapper;

    @Resource
    StaffMapper staffMapper;

    @Override
    public List<Commission> getAllCommissions() {
        return commissionMapper.getAllCommissions();
    }

    @Override
    public int insertCommission(Commission commission) {
        return commissionMapper.insertSelective(commission);
    }

    @Override
    public int deleteCommissionByID(Integer id) {
        return commissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCommission(Commission commission) {
        return commissionMapper.updateByPrimaryKeySelective(commission);
    }

    @Override
    public Commission selectCommissionByID(Integer id) {
        return commissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Order getOrderByCommID(Integer commissionID) {
        return orderMapper.getOrderByCommID(commissionID);
    }

    @Override
    public int setCommStatus(Integer commissionID, Integer status) {
        Commission commission = commissionMapper.selectByPrimaryKey(commissionID);
        if (commission == null) return -1;
        int tmp = status;
        commission.setStatus((byte)tmp);
        commissionMapper.updateByPrimaryKeySelective(commission);
        return commissionID;
    }

    @Override
    public int getTotalCost(Integer commissionID) {
        Order order = orderMapper.getOrderByCommID(commissionID);
        if (order == null) return -1;
        String d = order.getRepairDetail();
        if (d == null) return 0;
        List<RepairDetail> details = getDetails(d);
        int sum = 0;
        for (RepairDetail detail: details) {
            int rpID = detail.getRepairmentID();
            Repairment repairment = repairmentMapper.selectByPrimaryKey(rpID);
            if (repairment == null) continue;
            String m = repairment.getMaterial();
            if (m == null) continue;
            Staff s = staffMapper.selectByPrimaryKey(detail.WorkerID);
            if (s == null) continue;
            List<MaterialItem> items = getItems(m);
            for (MaterialItem i: items) {
                Material mt = materialMapper.selectByPrimaryKey(i.materialID);
                if (mt == null) continue;
                sum += i.count * mt.getPrice();
            }
            sum += s.getUnitPrice() * detail.getWorkTime();
        }
        return sum;
    }

    public List<RepairDetail> getDetails(String detail) {
        Gson gson = new Gson();
        return gson.fromJson(detail, new TypeToken<List<RepairDetail>>(){}.getType());
    }

    private List<MaterialItem> getItems(String item) {
        Gson gson = new Gson();
        return gson.fromJson(item, new TypeToken<List<MaterialItem>>(){}.getType());
    }
}
