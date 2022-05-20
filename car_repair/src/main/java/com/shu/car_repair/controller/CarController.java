package com.shu.car_repair.controller;

import com.shu.car_repair.dto.QueryCarDto;
import com.shu.car_repair.entity.Car;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.result.ResultCode;
import com.shu.car_repair.service.CarService;
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
@RequestMapping("/api/car")
@Api(tags = "2-车相关")
public class CarController {
    @Resource
    CarService carService;

    @GetMapping("/getAllCars")
    @ApiOperation(value = "查看所有车辆")
    @ResponseBody
    public Result getAllCars() {
        return Result.success(carService.getAllCars());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加车辆")
    @ResponseBody
    public Result insert(@RequestBody Car Car) {
        return Result.success(carService.insertCar(Car));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除车辆")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        int res = carService.deleteCarByID(id);
        if (res == -1) {
            Result.failure(ResultCode.CLIENT_ONLY_HAVE_ONE_CAR);
        }
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改车辆")
    @ResponseBody
    public Result update(@RequestBody Car Car) {
        return Result.success(carService.updateCar(Car));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询车辆")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(carService.selectCarByID(id));
    }

    @PostMapping("/queryByCon")
    @ApiOperation(value = "根据条件查询车辆")
    @ResponseBody
    public Result query(@RequestBody QueryCarDto dto) {
        return Result.success(carService.queryCarByCon(dto));
    }
}
