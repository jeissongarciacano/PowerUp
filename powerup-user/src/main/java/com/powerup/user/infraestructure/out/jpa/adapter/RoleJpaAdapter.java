package com.powerup.user.infraestructure.out.jpa.adapter;

import com.powerup.user.domain.model.Role;
import com.powerup.user.infraestructure.out.jpa.entity.RoleEntity;
import com.powerup.user.infraestructure.out.jpa.mapper.IRoleMapper;
import com.powerup.user.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleJpaAdapter {
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;
    public Role getRole(Long id){
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        Role role = new Role(roleEntity.get().getId(),roleEntity.get().getName(),roleEntity.get().getDescription());
        return role;
    }
    public RoleEntity toRoleEntity(Role role){
        return roleMapper.toEntity(role);
    }
    public List<RoleEntity> getAllRole(){
        return roleRepository.findAll();
    }
    public boolean existByID(Long id) {
        return roleRepository.existsById(id);
    }
}
