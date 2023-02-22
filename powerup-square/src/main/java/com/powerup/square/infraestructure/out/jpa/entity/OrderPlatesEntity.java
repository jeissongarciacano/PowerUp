package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "order_plates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPlatesEntity {
    @ManyToOne
    @Column(name = "id_order", nullable = false)
    private OrderEntity orderEntity;
    @ManyToOne
    @Column(name = "id_plate", nullable = false)
    private PlateEntity plateEntity;
    @NotBlank
    @Column(name = "amount", nullable = false)
    private Long amount;

}
