package com.shu.car_repair.service.impl;

import com.shu.car_repair.dto.QueryStaffDto;
import com.shu.car_repair.entity.Staff;
import com.shu.car_repair.mapper.StaffMapper;
import com.shu.car_repair.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    StaffMapper staffMapper;

    @Override
    public Staff login(String username, String password) {
        Staff staff = staffMapper.getPasswordByUsername(username);
        if (staff == null || !staff.getPassword().equals(password)) {
            return null;
        }
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffMapper.getAllStaff();
    }

    @Override
    public int insertStaff(Staff staff) {
        return staffMapper.insertSelective(staff);
    }

    @Override
    public int deleteStaffByID(Integer id) {
        return staffMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStaff(Staff staff) {
        return staffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public Staff selectStaffByID(Integer id) {
        return staffMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Staff> getAllBizMen() {
        return staffMapper.getAllBizMen();
    }

    @Override
    public List<Staff> getAllWorkers() {
        return staffMapper.getAllWorkers();
    }

    @Override
    public List<Staff> queryWorkerByPrice(QueryStaffDto dto) {
        dto.name = "%" + dto.name + "%";
        return staffMapper.queryWorkerByPrice(dto);
    }
}
