package com.powerup.user.infraestructure.out.jpa.adapter;

import com.powerup.user.domain.model.User;
import com.powerup.user.domain.spi.IUserPersistencePort;
import com.powerup.user.infraestructure.out.jpa.entity.UserEntity;
import com.powerup.user.infraestructure.out.jpa.mapper.IUserMapper;
import com.powerup.user.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toUser(userRepository.save(userEntity));
    }
    @Override
    public User getUser(Long id) {
        return userMapper.toUser(userRepository.findById(id).get());
    }
    @Override
    public User getUserByEmail(String email) {
        return userMapper.toUser(userRepository.findByEmail(email).get());
    }
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByID(Long id) {
        return userRepository.existsById(id);
    }
}
