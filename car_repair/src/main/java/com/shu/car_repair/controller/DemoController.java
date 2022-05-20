package com.shu.car_repair.controller;

import com.shu.car_repair.result.Result;
import com.shu.car_repair.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@RestController
@RequestMapping("/api/demo")
@Api(tags = "0-demo")
public class DemoController {
    @Resource
    CarService carService;

    @GetMapping("/getAllCars")
    @ApiOperation(value = "查询所有车")
    @ResponseBody
    public Result getFoodByNamespaceId() {
        return Result.success(carService.getAllCars());
    }
}
