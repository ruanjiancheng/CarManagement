package com.shu.car_repair.mapper;

import com.shu.car_repair.dto.QueryMaterialDto;
import com.shu.car_repair.entity.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> getAllMaterials();

    List<Material> queryMaterialByPrice(QueryMaterialDto dto);
}