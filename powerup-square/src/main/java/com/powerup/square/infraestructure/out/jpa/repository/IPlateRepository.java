package com.powerup.square.infraestructure.out.jpa.repository;

import org.springframework.data.domain.Page;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {

    boolean existsByName(String name);
    @Query(value = "SELECT * FROM public.plate where id_restaurant = :idRestaurant", nativeQuery = true)
    Page<PlateEntity> findByIdRestaurant(@Param("idRestaurant") Long idRestaurant, Pageable pagination);
}
