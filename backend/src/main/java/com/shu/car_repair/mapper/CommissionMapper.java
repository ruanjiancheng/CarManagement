package com.shu.car_repair.mapper;

import com.shu.car_repair.entity.Commission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commission record);

    int insertSelective(Commission record);

    Commission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commission record);

    int updateByPrimaryKey(Commission record);

    List<Commission> getAllCommissions();
}