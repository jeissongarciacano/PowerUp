package com.PowerUp.User.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @NotBlank
    @Column(name = "lastname", nullable = false, length = 60)
    private String lastName;
    @NotBlank
    @Column(name = "phone", nullable = false, length = 60)
    private String phone;
    @NotBlank
    @Column(name = "email", nullable = false, length = 60)
    private String email;
    @NotBlank
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @JoinColumn(name = "id_role", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch =
    FetchType.EAGER)
    private RoleEntity role;
    @NotBlank
    @Column(name = "id_document", nullable = false, length = 60)
    private String idDocument;
}
