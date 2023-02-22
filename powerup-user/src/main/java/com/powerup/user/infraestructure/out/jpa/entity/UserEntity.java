package com.powerup.user.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "El campo nombre es obligatorio")
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @NotBlank(message = "El campo apellido es obligatorio")
    @Column(name = "lastname", nullable = false, length = 60)
    private String lastName;
    @NotBlank(message = "El campo celular es obligatorio")
    @Pattern(regexp = "^(\\+57)?3\\d{9}$")
    @Column(name = "phone", nullable = false, length = 60)
    private String phone;
    @NotBlank(message = "El campo correo es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    @NotBlank
    @Column(name = "email", nullable = false, length = 60)
    private String email;
    @NotBlank(message = "El campo contraseña es obligatorio")
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @JoinColumn(name = "id_role", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch =
    FetchType.EAGER)
    private RoleEntity role;
    @NotBlank(message = "El campo documento es obligatorio")
    @Pattern(regexp = "^[0-9]*$", message = "debe ser numerico")
    @Size(min = 5, max=11, message = "no es valida")
    @Column(name = "id_document", nullable = false, length = 60)
    private String idDocument;
}
