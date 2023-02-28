package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_plates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPlatesEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_orders", nullable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "id_plate", nullable = false)
    private PlateEntity plate;
    @JoinColumn(name = "amount", nullable = false)
    private Long amount;

}
