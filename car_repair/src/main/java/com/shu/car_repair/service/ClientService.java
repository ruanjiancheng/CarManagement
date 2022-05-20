package com.shu.car_repair.service;


import com.shu.car_repair.dto.QueryClientDto;
import com.shu.car_repair.entity.Car;
import com.shu.car_repair.entity.Client;

import java.util.List;

/**
 * @author oxotn3
 * @create 2022-05-15
 * @description
 */
public interface ClientService {
    Client login(String username, String password);

    List<Car> getClientCars(String clientID);

    List<Client> getAllClients();

    int insertClient(Client client);

    int deleteClientByID(Integer id);

    int updateClient(Client client);

    Client selectClientByID(Integer id);

    List<Client> queryClientByCountAndRate(QueryClientDto dto);

    int incrCarCountByID(String clientID);

    Client getClientByClientID(String clientID);
}
