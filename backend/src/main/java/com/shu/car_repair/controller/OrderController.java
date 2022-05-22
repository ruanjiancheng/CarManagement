package com.shu.car_repair.controller;

import com.shu.car_repair.dto.AddRepairmentDto;
import com.shu.car_repair.dto.DeleteDetailDto;
import com.shu.car_repair.dto.InsertDetailDto;
import com.shu.car_repair.dto.UpdateOrderStatusDto;
import com.shu.car_repair.entity.Order;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.service.OrderService;
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
@RequestMapping("/api/order")
@Api(tags = "5-工单相关")
public class OrderController {
    @Resource
    OrderService orderService;

    @GetMapping("/getAllOrders")
    @ApiOperation(value = "查看所有工单")
    @ResponseBody
    public Result getAllOrders() {
        return Result.success(orderService.getAllOrders());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加工单")
    @ResponseBody
    public Result insert(@RequestBody Order Order) {
        return Result.success(orderService.insertOrder(Order));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除工单")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(orderService.deleteOrderByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改工单")
    @ResponseBody
    public Result update(@RequestBody Order Order) {
        return Result.success(orderService.updateOrder(Order));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询工单")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(orderService.selectOrderByID(id));
    }

    @GetMapping("/queryDetail")
    @ApiOperation(value = "查询详细维修项目，返回维修项目list")
    @ResponseBody
    public Result queryDetail(@RequestParam Integer id) {
        return Result.success(orderService.getRepairDetailByID(id));
    }

    @PostMapping("/insertDetail")
    @ApiOperation(value = "新增维修项目")
    @ResponseBody
    public Result insertDetail(@RequestBody InsertDetailDto dto) {
        return Result.success(orderService.insertDetail(dto.orderID, dto.detailID, dto.newDetailJson));
    }

    @PostMapping("/updateDetail")
    @ApiOperation(value = "更新维修项目")
    @ResponseBody
    public Result updateDetail(@RequestBody InsertDetailDto dto) {
        return Result.success(orderService.updateDetail(dto.orderID, dto.detailID, dto.newDetailJson));
    }

    @PostMapping("/deleteDetail")
    @ApiOperation(value = "删除维修项目")
    @ResponseBody
    public Result deleteDetail(@RequestBody DeleteDetailDto dto) {
        return Result.success(orderService.deleteDetail(dto.orderID, dto.detailID));
    }

    @PostMapping("/queryByWorkerID")
    @ApiOperation(value = "根据修理工id查询维修项目")
    @ResponseBody
    public Result queryByWorkerID(@RequestBody Integer id) {
        return Result.success(orderService.getOrderByWorkerID(id));
    }

    @GetMapping("/queryFinished")
    @ApiOperation(value = "根据修理工id查询【已完成】维修项目")
    @ResponseBody
    public Result queryFinished(@RequestParam Integer id) {
        return Result.success(orderService.getFinishedOrderByWorkerID(id));
    }

    @GetMapping("/queryUnfinished")
    @ApiOperation(value = "根据修理工id查询【未完成】维修项目")
    @ResponseBody
    public Result queryUnfinished(@RequestParam Integer id) {
        return Result.success(orderService.getUnfinishedOrderByWorkerID(id));
    }


    @PostMapping("/finishOneRepair")
    @ApiOperation(value = "根据给定工单id和维修项目id，标记该项目为已完成")
    @ResponseBody
    public Result finishOneRepair(@RequestBody DeleteDetailDto dto) {
        return Result.success(orderService.finishOneRepair(dto.orderID, dto.detailID));
    }


    @PostMapping("/addRepairment")
    @ApiOperation(value = "新增一条维修项目")
    @ResponseBody
    public Result addRepairment(@RequestBody AddRepairmentDto dto) {
        return Result.success(orderService.addRepairment(dto.orderID, dto.detailID, dto.workerID, dto.workTime));
    }


    @PostMapping("/updateOrderStatus")
    @ApiOperation(value = "更新工单状态")
    @ResponseBody
    public Result updateOrderStatus(@RequestBody UpdateOrderStatusDto dto) {
        return Result.success(orderService.updateOrderStatus(dto.orderID, dto.status));
    }
}
