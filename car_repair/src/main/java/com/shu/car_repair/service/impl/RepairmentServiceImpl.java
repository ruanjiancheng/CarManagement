package com.shu.car_repair.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shu.car_repair.dto.MaterialDto;
import com.shu.car_repair.dto.OrderDto;
import com.shu.car_repair.dto.RepairmentDto;
import com.shu.car_repair.entity.Material;
import com.shu.car_repair.entity.Repairment;
import com.shu.car_repair.entity.sub.MaterialItem;
import com.shu.car_repair.entity.sub.RepairDetail;
import com.shu.car_repair.mapper.MaterialMapper;
import com.shu.car_repair.mapper.RepairmentMapper;
import com.shu.car_repair.service.RepairmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class RepairmentServiceImpl implements RepairmentService {
    @Resource
    RepairmentMapper repairmentMapper;

    @Resource
    MaterialMapper materialMapper;

    @Override
    public List<Repairment> getAllRepairments() {
        return repairmentMapper.getAllRepairments();
    }

    @Override
    public int insertRepairment(Repairment repairment) {
        return repairmentMapper.insertSelective(repairment);
    }

    @Override
    public int deleteRepairmentByID(Integer id) {
        return repairmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRepairment(Repairment repairment) {
        return repairmentMapper.updateByPrimaryKeySelective(repairment);
    }

    @Override
    public Repairment selectRepairmentByID(Integer id) {
        return repairmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertRepairmentJson(String json) {
        return 0;
    }

    @Override
    public int insertMaterial(Integer repairmentID, Integer materialID, Integer count) {
        Repairment repairment = repairmentMapper.selectByPrimaryKey(repairmentID);
        if (repairment == null) return -1;
        RepairmentDto dto = transferToDto(repairment);
        List<MaterialItem> tmp = new ArrayList<>();
        boolean exist = false;

        if (dto.getMaterial() != null) {
            for (MaterialItem i: dto.getMaterial()) {
                MaterialItem item = new MaterialItem();
                item.setCount(i.count);
                item.setMaterialID(i.materialID);
                if (i.getMaterialID().equals(materialID)) {
                    exist = true;
                    item.setCount(i.count + count);
                }
                tmp.add(item);
            }
        }
        if (!exist) {
            MaterialItem item = new MaterialItem();
            item.setMaterialID(materialID);
            item.setCount(count);
            tmp.add(item);
        }
        dto.setMaterial(tmp);
        Repairment res = transferToEntity(dto);
        repairmentMapper.updateByPrimaryKeySelective(res);
        return repairmentID;
    }

    @Override
    public List<MaterialDto> queryMaterialByRepairID(Integer id) {
        List<MaterialDto> res = new ArrayList<>();
        Repairment repairment = repairmentMapper.selectByPrimaryKey(id);
        if (repairment == null) return res;
        List<MaterialItem> items = getItems(repairment.getMaterial());
        if (items == null) {
            return res;
        }
        for (MaterialItem i: items) {
            MaterialDto dto = new MaterialDto();
            dto.setMaterialID(i.materialID);
            dto.setCount(i.count);
            Material material = materialMapper.selectByPrimaryKey(i.materialID);
            dto.setName(material.getName());
            dto.setPrice(material.getPrice());
            res.add(dto);
        }
        return res;
    }

    private RepairmentDto transferToDto(Repairment repairment) {
        RepairmentDto dto = new RepairmentDto();
        dto.setId(repairment.getId());
        dto.setName(repairment.getName());
        dto.setRepairmentId(repairment.getRepairmentId());

        List<MaterialItem> items = getItems(repairment.getMaterial());
        dto.setMaterial(items);
        return dto;
    }

    private Repairment transferToEntity(RepairmentDto dto) {
        Repairment repairment = new Repairment();
        repairment.setId(dto.getId());
        repairment.setName(dto.getName());
        repairment.setRepairmentId(dto.getRepairmentId());
        repairment.setMaterial(getItemsString(dto.getMaterial()));
        return repairment;
    }

    private List<MaterialItem> getItems(String item) {
        Gson gson = new Gson();
        return gson.fromJson(item, new TypeToken<List<MaterialItem>>(){}.getType());
    }

    private String getItemsString(List<MaterialItem> items) {
        Gson gson = new Gson();
        return gson.toJson(items);
    }
}
