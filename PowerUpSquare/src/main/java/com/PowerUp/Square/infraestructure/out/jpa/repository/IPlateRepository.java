package com.PowerUp.Square.infraestructure.out.jpa.repository;

import com.PowerUp.Square.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
}
