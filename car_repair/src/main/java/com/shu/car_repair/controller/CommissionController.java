package com.shu.car_repair.controller;

import com.shu.car_repair.dto.SetCommStatusDto;
import com.shu.car_repair.entity.Commission;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.service.CommissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@RestController
@RequestMapping("/api/comm")
@Api(tags = "3-委托书相关")
public class CommissionController {
    @Resource
    CommissionService commissionService;

    @GetMapping("/getAllCommissions")
    @ApiOperation(value = "查看所有委托书")
    @ResponseBody
    public Result getAllCommissions() {
        return Result.success(commissionService.getAllCommissions());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加委托书")
    @ResponseBody
    public Result insert(@RequestBody Commission Commission) {
        return Result.success(commissionService.insertCommission(Commission));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除委托书")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(commissionService.deleteCommissionByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改委托书")
    @ResponseBody
    public Result update(@RequestBody Commission Commission) {
        return Result.success(commissionService.updateCommission(Commission));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询委托书")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(commissionService.selectCommissionByID(id));
    }


    @GetMapping("/getOrderByCommID")
    @ApiOperation(value = "根据委托书id查询工单")
    @ResponseBody
    public Result getOrderByCommID(@RequestParam Integer id) {
        return Result.success(commissionService.getOrderByCommID(id));
    }


    @PostMapping("/setCommStatus")
    @ApiOperation(value = "设置委托书状态")
    @ResponseBody
    public Result setCommStatus(@RequestBody SetCommStatusDto dto) {
        return Result.success(commissionService.setCommStatus(dto.commissionID, dto.status));
    }

    @GetMapping("/getTotalCost")
    @ApiOperation(value = "获取总价格")
    @ResponseBody
    public Result getTotalCost(@RequestParam Integer commissionID) {
        return Result.success(commissionService.getTotalCost(commissionID));
    }
}
