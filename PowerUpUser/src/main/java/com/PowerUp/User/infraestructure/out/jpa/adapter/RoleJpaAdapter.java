package com.PowerUp.User.infraestructure.out.jpa.adapter;

import com.PowerUp.User.infraestructure.out.jpa.entity.RoleEntity;
import com.PowerUp.User.infraestructure.out.jpa.mapper.IRoleMapper;
import com.PowerUp.User.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleJpaAdapter {
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    public RoleEntity saveRoleEntity(RoleEntity roleEntity){
        return roleRepository.save(roleEntity);
    }
    public List<RoleEntity> getAllRole(){
        return roleRepository.findAll();
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    public RoleEntity editRole(RoleEntity roleEntity){
        if(roleRepository.existsById(roleEntity.getId())){
            return roleRepository.save(roleEntity);
        }
        return null;
    }

    public boolean existByID(Long id) {
        return roleRepository.existsById(id);
    }
}
