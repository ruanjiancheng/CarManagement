package com.shu.car_repair.mapper;

import com.shu.car_repair.dto.QueryClientDto;
import com.shu.car_repair.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    Client getPasswordByUsername(String username);

    List<Client> getAllClients();

    List<Client> queryClientByCountAndRate(QueryClientDto dto);

    int incrCarCountByID(String clientID);

    Client getClientByClientID(String clientID);
}