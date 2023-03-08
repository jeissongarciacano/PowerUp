package com.powerup.user.infraestructure;

import com.powerup.user.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.powerup.user.infraestructure.out.jpa.mapper.IRoleMapper;
import com.powerup.user.infraestructure.out.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

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
        roleJpaAdapter.getAllRoles();
        verify(roleMapper).toRoles(roleRepository.findAll());
    }

    @Test
    void getRoleById() {

        Long Random = new Random().nextLong(0,4);
        verify(roleMapper).toRole(roleRepository.findById(Random).get());
    }

}