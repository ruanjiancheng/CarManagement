package com.shu.car_repair.service;

import com.shu.car_repair.dto.MaterialDto;
import com.shu.car_repair.entity.Client;
import com.shu.car_repair.entity.Repairment;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface RepairmentService {
    List<Repairment> getAllRepairments();

    int insertRepairment(Repairment repairment);

    int deleteRepairmentByID(Integer id);

    int updateRepairment(Repairment repairment);

    Repairment selectRepairmentByID(Integer id);

    int insertRepairmentJson(String json);

    int insertMaterial(Integer repairmentID, Integer materialID, Integer count);

    List<MaterialDto> queryMaterialByRepairID(Integer id);
}
