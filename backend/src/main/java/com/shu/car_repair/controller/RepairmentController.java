package com.shu.car_repair.controller;

import com.shu.car_repair.dto.InsertMaterialDto;
import com.shu.car_repair.entity.Repairment;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.service.RepairmentService;
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
@RequestMapping("/api/repair")
@Api(tags = "6-维修项目相关")
public class RepairmentController {
    @Resource
    RepairmentService repairmentService;

    @GetMapping("/getAllRepairments")
    @ApiOperation(value = "查看所有维修项目")
    @ResponseBody
    public Result getAllRepairments() {
        return Result.success(repairmentService.getAllRepairments());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加维修项目")
    @ResponseBody
    public Result insert(@RequestBody Repairment Repairment) {
        return Result.success(repairmentService.insertRepairment(Repairment));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除维修项目")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(repairmentService.deleteRepairmentByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改维修项目")
    @ResponseBody
    public Result update(@RequestBody Repairment Repairment) {
        return Result.success(repairmentService.updateRepairment(Repairment));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询维修项目")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(repairmentService.selectRepairmentByID(id));
    }

    @PostMapping("/insertMaterial")
    @ApiOperation(value = "插入材料")
    @ResponseBody
    public Result insertMaterial(@RequestBody InsertMaterialDto dto) {
        return Result.success(repairmentService.insertMaterial(dto.repairmentID, dto.materialID, dto.count));
    }


    @GetMapping("/queryMaterialByRepairID")
    @ApiOperation(value = "根据维修项目ID查询对应材料详细信息")
    @ResponseBody
    public Result queryMaterialByRepairID(@RequestParam Integer repairmentID) {
        return Result.success(repairmentService.queryMaterialByRepairID(repairmentID));
    }
}
