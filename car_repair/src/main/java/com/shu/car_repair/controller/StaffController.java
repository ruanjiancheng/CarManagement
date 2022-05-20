package com.shu.car_repair.controller;

import com.shu.car_repair.dto.LoginDto;
import com.shu.car_repair.dto.QueryStaffDto;
import com.shu.car_repair.entity.Client;
import com.shu.car_repair.entity.Staff;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.result.ResultCode;
import com.shu.car_repair.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@RestController
@RequestMapping("/api/staff")
@Api(tags = "7-员工相关")
public class StaffController {
    @Resource
    StaffService staffService;

    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    @ResponseBody
    public Result staffLogin(@RequestBody LoginDto dto) {
        Staff staff = staffService.login(dto.username, dto.password);
        if (staff == null) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        return Result.success(staff);
    }

    @GetMapping("/getAllStaffs")
    @ApiOperation(value = "查看所有员工")
    @ResponseBody
    public Result getAllStaffs() {
        return Result.success(staffService.getAllStaff());
    }

    @GetMapping("/getAllBizMen")
    @ApiOperation(value = "查看所有业务员")
    @ResponseBody
    public Result getAllBizMen() {
        return Result.success(staffService.getAllBizMen());
    }

    @GetMapping("/getAllWorkers")
    @ApiOperation(value = "查看所有维修员")
    @ResponseBody
    public Result getAllWorkers() {
        return Result.success(staffService.getAllWorkers());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加员工")
    @ResponseBody
    public Result insert(@RequestBody Staff Staff) {
        return Result.success(staffService.insertStaff(Staff));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除员工")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(staffService.deleteStaffByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改员工")
    @ResponseBody
    public Result update(@RequestBody Staff Staff) {
        return Result.success(staffService.updateStaff(Staff));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询员工")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(staffService.selectStaffByID(id));
    }

    @PostMapping("/queryWorker")
    @ApiOperation(value = "根据单价和姓名查询维修工")
    @ResponseBody
    public Result queryWorker(@RequestBody QueryStaffDto dto) {
        List<Staff> staff = staffService.queryWorkerByPrice(dto);
        if (staff == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(staff);
    }
}
