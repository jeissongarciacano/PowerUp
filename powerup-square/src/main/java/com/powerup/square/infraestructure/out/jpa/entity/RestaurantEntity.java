package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "El campo nombre es obligatorio")
    @Pattern(regexp = "^(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message = "no debe ser solo numerico")
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @NotBlank(message = "El campo direccion es obligatorio")
    @Column(name = "address", nullable = false, length = 60)
    private String address;
    @Column(name = "id_owner", nullable = false, length = 60)
    private Long idOwner;
    @NotBlank(message = "El campo celular es obligatorio")
    @Pattern(regexp = "^[0-9]*$", message = "debe ser numerico")
    @NotBlank
    @Column(name = "phone", nullable = false, length = 60)
    private String phone;
    @NotBlank(message = "El campo url es obligatorio")
    @Column(name = "urlLogo", nullable = false, length = 60)
    private String urlLogo;

    @NotBlank(message = "El campo nit es obligatorio")
    @Pattern(regexp = "^(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message = "no debe ser solo numerico")
    @Column(name = "nit", nullable = false, length = 60)
    private String nit;
    @OneToMany(mappedBy = "restaurant")
    private List<PlateEntity> plates;

}
