package com.PowerUp.User.infraestructure.out.jpa.adapter;

import com.PowerUp.User.domain.model.User;
import com.PowerUp.User.domain.spi.IUserPersistencePort;
import com.PowerUp.User.infraestructure.out.jpa.mapper.IUserMapper;
import com.PowerUp.User.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return userMapper.toUser(userRepository.findById(id));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userMapper.toEntity(user));
    }

    /* UserEntity saveUserEntity(UserEntity userEntity){
        return iUserRepository.save(userEntity);
    }
    public List<UserEntity> getAllUser(){
        return iUserRepository.findAll();
    }

    public void deleteUser(Long id){
        iUserRepository.deleteById(id);
    }

    public UserEntity editUser(UserEntity userEntity){
        if(iUserRepository.existsById(userEntity.getId())){
            return iUserRepository.save(userEntity);
        }
        return null;
    }

    public boolean existByID(Long id) {
        return iUserRepository.existsById(id);
    }*/
}
