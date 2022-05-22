package com.shu.car_repair.service;

import com.shu.car_repair.dto.QueryStaffDto;
import com.shu.car_repair.entity.Staff;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface StaffService {
    Staff login(String username, String password);

    List<Staff> getAllStaff();

    int insertStaff(Staff staff);

    int deleteStaffByID(Integer id);

    int updateStaff(Staff staff);

    Staff selectStaffByID(Integer id);

    List<Staff> getAllBizMen();

    List<Staff> getAllWorkers();

    List<Staff> queryWorkerByPrice(QueryStaffDto dto);
}
