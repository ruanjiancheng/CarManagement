package com.shu.car_repair.mapper;


import com.shu.car_repair.dto.QueryStaffDto;
import com.shu.car_repair.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    Staff getPasswordByUsername(String username);

    List<Staff> getAllStaff();

    List<Staff> getAllBizMen();

    List<Staff> getAllWorkers();

    List<Staff> queryWorkerByPrice(QueryStaffDto dto);
}