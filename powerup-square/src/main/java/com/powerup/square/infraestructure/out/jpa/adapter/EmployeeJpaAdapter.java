package com.PowerUp.Square.infraestructure.out.jpa.adapter;

import com.PowerUp.Square.infraestructure.out.jpa.entity.EmployeeEntity;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeJpaAdapter {
    public final IEmployeeRepository employeeRepository;
    public final IEmployeeMapper employeeMapper;

    public EmployeeEntity saveEmployeeEntity(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
    public List<EmployeeEntity> getAllCategory(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public EmployeeEntity editEmployee(EmployeeEntity employeeEntity){
        if(employeeRepository.existsById(employeeEntity.getIdUser())){
            return employeeRepository.save(employeeEntity);
        }
        return null;
    }
    public boolean existByID(Long id) {
        return employeeRepository.existsById(id);
    }
}
