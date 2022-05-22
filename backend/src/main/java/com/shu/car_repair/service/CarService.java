package com.shu.car_repair.service;

import com.shu.car_repair.dto.QueryCarDto;
import com.shu.car_repair.entity.Car;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface CarService {
    List<Car> getAllCars();

    int insertCar(Car car);

    int deleteCarByID(Integer id);

    int updateCar(Car car);

    Car selectCarByID(Integer id);

    List<Car> queryCarByCon(QueryCarDto dto);
}
