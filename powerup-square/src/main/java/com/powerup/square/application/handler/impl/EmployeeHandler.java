package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {
    private final IEmployeeServicePort iEmployeeServicePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort, IRestaurantPersistencePort iRestaurantPersistencePort) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
    }

    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        if(!iRestaurantPersistencePort.existByIdOwner(employeeRequest.getIdOwner())) throw new NoDataFoundException();
        else{
            Restaurant restaurant = iRestaurantPersistencePort.getRestaurantByIdOwner(employeeRequest.getIdOwner());
            Employee employee = new Employee(-1L, restaurant, employeeRequest.getIdUser(), employeeRequest.getField());
            iEmployeeServicePort.saveEmployee(employee);
        }
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        return null;
    }
}
