package com.PowerUp.User.infraestructure.out.jpa.adapter;

import com.PowerUp.User.domain.model.Role;
import com.PowerUp.User.domain.model.User;
import com.PowerUp.User.domain.spi.IUserPersistencePort;
import com.PowerUp.User.infraestructure.out.jpa.entity.RoleEntity;
import com.PowerUp.User.infraestructure.out.jpa.entity.UserEntity;
import com.PowerUp.User.infraestructure.out.jpa.mapper.IUserMapper;
import com.PowerUp.User.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final RoleJpaAdapter roleJpaAdapter;
    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        Role role = roleJpaAdapter.getRole(user.getIdRole());
        RoleEntity roleEntity = roleJpaAdapter.toRoleEntity(role);
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
    }
    @Override
    public User getUser(Long id) {
        return userMapper.toUser(userRepository.findById(id));
    }
    public boolean existByID(Long id) {
        return userRepository.existsById(id);
    }
}
