package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByIdClientAndState(Long idClient, String state);
    @Query(value = "SELECT * FROM public.orders where id_restaurant = :idRestaurant and state = :state", nativeQuery = true)
    Page<OrderEntity> findAllByRestaurantAndState(@Param("idRestaurant") Long idRestaurant,@Param("state") String state, Pageable pageable);
}
