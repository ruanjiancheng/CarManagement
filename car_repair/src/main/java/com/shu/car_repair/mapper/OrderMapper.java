package com.shu.car_repair.mapper;

import com.shu.car_repair.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getAllOrders();

    Order getOrderByCommID(Integer commissionID);
}