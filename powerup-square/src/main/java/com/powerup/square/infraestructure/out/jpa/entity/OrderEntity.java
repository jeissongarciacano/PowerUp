package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "order_dinner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "id_Client", nullable = false)
    private Long idClient;
    @NotBlank
    @Column(name = "date", nullable = false, length = 60)
    private String date;
    @NotBlank
    @Column(name = "state", nullable = false, length = 60)
    private String state;
    @ManyToOne
    @Column(name = "id_Chef", nullable = false)
    private EmployeeEntity idChef;
    @ManyToOne
    @JoinColumn(name = "id_Restaurant", nullable = false)
    private RestaurantEntity restaurant;
    @OneToMany(mappedBy = "order")
    private List<OrderPlatesEntity> orderPlatesEntities;

}
