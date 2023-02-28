package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser", nullable = false)
    private Long idUser;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;
    @Column(name = "field", nullable = false)
    private String field;

}
