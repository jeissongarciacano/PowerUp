package com.powerup.square.application;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;

public class SaveEmployeeHandlerDataTest {
    public static Employee obtainEmployee() {
        Employee employee = new Employee(10L,
                new Restaurant(
                100L,
                "Angus Hamburguers",
                "Street 25",
                1L,
                "3013218520",
                "www.logo.es",
                "ASD-121854-YU"
        ), 10L, "empleado");
        return employee;
    }

    public static EmployeeRequest obtainEmployeeRequest() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setField("empleado");
        employeeRequest.setIdOwner(1L);
        employeeRequest.setIdUser(10L);
        return employeeRequest;
    }
}
