package com.shu.car_repair.controller;

import com.shu.car_repair.dto.QueryMaterialDto;
import com.shu.car_repair.entity.Material;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.service.MaterialService;
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
@RequestMapping("/api/material")
@Api(tags = "4-材料相关")
public class MaterialController {
    @Resource
    MaterialService materialService;

    @GetMapping("/getAllMaterials")
    @ApiOperation(value = "查看所有材料")
    @ResponseBody
    public Result getAllMaterials() {
        return Result.success(materialService.getAllMaterials());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加材料")
    @ResponseBody
    public Result insert(@RequestBody Material Material) {
        return Result.success(materialService.insertMaterial(Material));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除材料")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(materialService.deleteMaterialByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改材料")
    @ResponseBody
    public Result update(@RequestBody Material Material) {
        return Result.success(materialService.updateMaterial(Material));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询材料")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(materialService.selectMaterialByID(id));
    }

    @PostMapping("/queryByPrice")
    @ApiOperation(value = "查询材料")
    @ResponseBody
    public Result queryByPrice(@RequestBody QueryMaterialDto dto) {
        return Result.success(materialService.queryMaterialByPrice(dto));
    }

}
