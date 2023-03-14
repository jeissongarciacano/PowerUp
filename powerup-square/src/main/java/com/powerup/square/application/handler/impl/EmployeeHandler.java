package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.user.EmployeeRequest;
import com.powerup.square.application.dto.user.EmployeeResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {
    private final IEmployeeServicePort iEmployeeServicePort;
    private final RestaurantHandler restaurantHandler;

    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort, RestaurantHandler restaurantHandler) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.restaurantHandler = restaurantHandler;
    }
    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        if(!restaurantHandler.existByIdOwner(employeeRequest.getIdOwner())) throw new NoDataFoundException();
        else{
            Restaurant restaurant = restaurantHandler.getRestaurantByIdOwner(employeeRequest.getIdOwner());
            Employee employee = new Employee(-1L, restaurant, employeeRequest.getIdUser(), employeeRequest.getField());
            iEmployeeServicePort.saveEmployee(employee);
        }
    }
    @Override
    public EmployeeResponse getEmployee(Long id) {
        return null;
    }
}
