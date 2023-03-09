package com.powerup.square.application;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.handler.impl.EmployeeHandler;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeHandlerTest {
    @InjectMocks
    EmployeeHandler employeeHandler;
    @Mock
    IEmployeeServicePort employeeServicePort;
    @Mock
    IEmployeePersistencePort employeePersistencePort;

    @Test
    void saveEmployee(){
        Employee employee = SaveEmployeeHandlerDataTest.obtainEmployee();
        EmployeeRequest employeeRequest = SaveEmployeeHandlerDataTest.obtainEmployeeRequest();

        employeeHandler.saveEmployee(employeeRequest);
        verify(employeeServicePort).saveEmployee(employee);
    }
}
