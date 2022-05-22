package com.shu.car_repair.service.impl;

import com.shu.car_repair.dto.QueryClientDto;
import com.shu.car_repair.entity.Car;
import com.shu.car_repair.entity.Client;
import com.shu.car_repair.mapper.CarMapper;
import com.shu.car_repair.mapper.ClientMapper;
import com.shu.car_repair.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Resource
    ClientMapper clientMapper;
    @Resource
    CarMapper carMapper;

    @Override
    public Client login(String username, String password) {
        Client client = clientMapper.getPasswordByUsername(username);
        if (client == null || !client.getPassword().equals(password)) {
            return null;
        }
        return client;
    }

    @Override
    public List<Car> getClientCars(String clientID) {
        return carMapper.getCarsByClientID(clientID);
    }

    @Override
    public List<Client> getAllClients() {
        return clientMapper.getAllClients();
    }

    @Override
    public int insertClient(Client client) {
        return clientMapper.insertSelective(client);
    }

    @Override
    public int deleteClientByID(Integer id) {
        return clientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateClient(Client client) {
        return clientMapper.updateByPrimaryKeySelective(client);
    }

    @Override
    public Client selectClientByID(Integer id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Client> queryClientByCountAndRate(QueryClientDto dto) {
        dto.name = "%" + dto.name + "%";
        return clientMapper.queryClientByCountAndRate(dto);
    }

    @Override
    public int incrCarCountByID(String clientID) {
        return clientMapper.incrCarCountByID(clientID);
    }

    @Override
    public Client getClientByClientID(String clientID) {
        return clientMapper.getClientByClientID(clientID);
    }
}
