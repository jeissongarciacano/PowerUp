package com.powerup.user.adapter;

import com.powerup.user.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.powerup.user.infraestructure.out.jpa.mapper.IRoleMapper;
import com.powerup.user.infraestructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RoleJpaAdapterTest {

    @InjectMocks
    RoleJpaAdapter roleJpaAdapter;

    @Mock
    IRoleRepository roleRepository;

    @Mock
    IRoleMapper roleMapper;

    @Test
    void getAllRoles() {
        //Given
        //When
        roleJpaAdapter.getAllRoles();

        //Then
        verify(roleMapper).toRoles(roleRepository.findAll());
    }

//    @Test
//    void getRoleById() {
//        Long id = 5L;
//        //When
//        roleJpaAdapter.getRoleById(id);
//
//        //Then
//        verify(roleMapper).toRole(roleRepository.findById(anyLong()).get());
//
//    }

//    @Test
//    void getRoleByName() {
//        //Given
//        String name = "juliana";
//
//        //When
//        roleJpaAdapter.getRoleByName(name);
//
//        RoleEntity roleEntity
//
//        //Then
//        verify(roleMapper).toRole(roleRepository.findByName(name));
//    }
}