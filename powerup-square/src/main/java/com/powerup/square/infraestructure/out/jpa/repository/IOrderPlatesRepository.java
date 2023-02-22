package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderPlatesRepository extends JpaRepository<OrderPlatesEntity, Long> {
}
