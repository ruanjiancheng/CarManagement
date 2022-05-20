package com.shu.car_repair.controller;

import com.shu.car_repair.dto.LoginDto;
import com.shu.car_repair.dto.QueryClientDto;
import com.shu.car_repair.entity.Client;
import com.shu.car_repair.result.Result;
import com.shu.car_repair.result.ResultCode;
import com.shu.car_repair.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@RestController
@RequestMapping("/api/client")
@Api(tags = "1-顾客相关")
public class ClientController {
    @Resource
    ClientService clientService;

    @GetMapping("/getAllClients")
    @ApiOperation(value = "查看所有顾客")
    @ResponseBody
    public Result getAllClients() {
        return Result.success(clientService.getAllClients());
    }

    @PostMapping("/login")
    @ApiOperation(value = "顾客登录")
    @ResponseBody
    public Result clientLogin(@RequestBody LoginDto dto) {
        Client client = clientService.login(dto.username, dto.password);
        if (client == null) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        return Result.success(client);
    }

    @GetMapping("/getClientCars")
    @ApiOperation(value = "查看顾客所有车")
    @ResponseBody
    public Result gerClientCars(@RequestParam String clientID) {
        return Result.success(clientService.getClientCars(clientID));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加顾客")
    @ResponseBody
    public Result insert(@RequestBody Client client) {
        return Result.success(clientService.insertClient(client));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除顾客")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        return Result.success(clientService.deleteClientByID(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改顾客")
    @ResponseBody
    public Result update(@RequestBody Client client) {
        return Result.success(clientService.updateClient(client));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询顾客")
    @ResponseBody
    public Result query(@RequestParam Integer id) {
        return Result.success(clientService.selectClientByID(id));
    }

    @PostMapping("/queryByCountAndRate")
    @ApiOperation(value = "根据车辆数、折扣率、姓名查询顾客")
    @ResponseBody
    public Result query(@RequestBody QueryClientDto dto) {
        return Result.success(clientService.queryClientByCountAndRate(dto));
    }


}
