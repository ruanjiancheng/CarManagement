package com.shu.car_repair.service.impl;

import com.shu.car_repair.dto.QueryMaterialDto;
import com.shu.car_repair.entity.Material;
import com.shu.car_repair.mapper.MaterialMapper;
import com.shu.car_repair.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Resource
    MaterialMapper materialMapper;

    @Override
    public List<Material> getAllMaterials() {
        return materialMapper.getAllMaterials();
    }

    @Override
    public int insertMaterial(Material material) {
        return materialMapper.insertSelective(material);
    }

    @Override
    public int deleteMaterialByID(Integer id) {
        return materialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateMaterial(Material material) {
        return materialMapper.updateByPrimaryKeySelective(material);
    }

    @Override
    public Material selectMaterialByID(Integer id) {
        return materialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Material> queryMaterialByPrice(QueryMaterialDto dto) {
        return materialMapper.queryMaterialByPrice(dto);
    }
}
