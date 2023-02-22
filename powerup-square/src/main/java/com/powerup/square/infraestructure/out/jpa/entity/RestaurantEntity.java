package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @NotBlank
    @Column(name = "address", nullable = false, length = 60)
    private String address;
    @NotBlank
    @Column(name = "id_owner", nullable = false, length = 60)
    private Long idOwner;
    @NotBlank
    @Column(name = "phone", nullable = false, length = 60)
    private String phone;
    @NotBlank
    @Column(name = "urlLogo", nullable = false, length = 60)
    private String urlLogo;
    @NotBlank
    @Column(name = "nit", nullable = false, length = 60)
    private String nit;
    @OneToMany(mappedBy = "restaurant")
    private List<PlateEntity> plates;
    @OneToMany(mappedBy = "restaurant")
    private List<OrderEntity> orders;


}
