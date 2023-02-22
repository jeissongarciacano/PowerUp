package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
