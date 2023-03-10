package com.powerup.square.application;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.handler.impl.EmployeeHandler;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeHandlerTest {
    @InjectMocks
    EmployeeHandler employeeHandler;
    @Mock
    IEmployeeServicePort employeeServicePort;
    @Mock
    IEmployeePersistencePort employeePersistencePort;
    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;

    @Test
    void saveEmployee(){
        Employee employee = SaveEmployeeHandlerDataTest.obtainEmployee();
        EmployeeRequest employeeRequest = SaveEmployeeHandlerDataTest.obtainEmployeeRequest();
        when(iRestaurantPersistencePort.existByIdOwner(anyLong())).thenReturn(true);
        when(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong())).thenReturn(SaveRestaurantHandlerDataTest.obtainRestaurant());
        employeeHandler.saveEmployee(employeeRequest);
        assertEquals(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong()).getId(), SaveRestaurantHandlerDataTest.obtainRestaurant().getId());
    }
}
