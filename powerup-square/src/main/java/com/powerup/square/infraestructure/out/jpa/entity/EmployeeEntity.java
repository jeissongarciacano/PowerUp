package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @NotBlank
    @Column(name = "id_user", nullable = false, length = 60)
    private Long idUser;
    @Column(name = "id_restaurant", nullable = false)
    private Long idRestaurant;
    @NotBlank
    @Column(name = "field", nullable = false, length = 60)
    private String field;
    @OneToMany(mappedBy = "employee")
    private List<OrderEntity> orders;


}
