package com.powerup.square.domain.api;

import com.powerup.square.domain.model.Employee;

import java.util.List;

public interface IEmployeeServicePort {
    void saveEmployee(Employee employee);
    Employee getEmployee(Long id);
}
