package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}
