package com.shu.car_repair.mapper;

import com.shu.car_repair.entity.Repairment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Repairment record);

    int insertSelective(Repairment record);

    Repairment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repairment record);

    int updateByPrimaryKeyWithBLOBs(Repairment record);

    int updateByPrimaryKey(Repairment record);

    List<Repairment> getAllRepairments();
}