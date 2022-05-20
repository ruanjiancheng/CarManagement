package com.shu.car_repair.mapper;

import com.shu.car_repair.dto.QueryCarDto;
import com.shu.car_repair.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<Car> selectAllCars();

    List<Car> getCarsByClientID(String clientID);

    List<Car> queryCarByCon(QueryCarDto dto);
}