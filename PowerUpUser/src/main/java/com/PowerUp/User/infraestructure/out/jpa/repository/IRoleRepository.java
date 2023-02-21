package com.PowerUp.User.infraestructure.out.jpa.repository;

import com.PowerUp.User.infraestructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
