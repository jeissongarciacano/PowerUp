package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Employee;

import java.util.List;

public interface IEmployeePersistencePort {
    void saveEmployee(Employee employee);
    Employee getEmployee(Long id);
}
