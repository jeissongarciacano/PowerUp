package com.powerup.square.application.handler;

import com.powerup.square.application.dto.user.EmployeeRequest;
import com.powerup.square.application.dto.user.EmployeeResponse;

public interface IEmployeeHandler {
    void saveEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getEmployee(Long id);
}
