package com.shu.car_repair.service;

import com.shu.car_repair.dto.QueryMaterialDto;
import com.shu.car_repair.entity.Material;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface MaterialService {
    List<Material> getAllMaterials();

    int insertMaterial(Material material);

    int deleteMaterialByID(Integer id);

    int updateMaterial(Material material);

    Material selectMaterialByID(Integer id);

    List<Material> queryMaterialByPrice(QueryMaterialDto dto);
}
