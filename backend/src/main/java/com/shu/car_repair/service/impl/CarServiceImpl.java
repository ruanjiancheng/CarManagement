package com.shu.car_repair.service.impl;

import com.shu.car_repair.dto.QueryCarDto;
import com.shu.car_repair.entity.Car;
import com.shu.car_repair.entity.Client;
import com.shu.car_repair.mapper.CarMapper;
import com.shu.car_repair.service.CarService;
import com.shu.car_repair.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class CarServiceImpl implements CarService {
    @Resource
    CarMapper carMapper;

    @Resource
    ClientService clientService;

    @Override
    public List<Car> getAllCars() {
        return carMapper.selectAllCars();
    }

    @Override
    public int insertCar(Car car) {
        int res = clientService.incrCarCountByID(car.getClientId());
        return carMapper.insertSelective(car);
    }

    @Override
    public int deleteCarByID(Integer id) {
        Car car = carMapper.selectByPrimaryKey(id);
        Client client = clientService.getClientByClientID(car.getClientId());
        if (client.getCarCount() <= 1) {
            return -1;
        }
        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public Car selectCarByID(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Car> queryCarByCon(QueryCarDto dto) {
        return carMapper.queryCarByCon(dto);
    }
}
