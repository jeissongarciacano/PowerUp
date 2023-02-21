package com.PowerUp.Square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @NotBlank
    @Column(name = "description", nullable = false, length = 60)
    private String description;
    @OneToMany(mappedBy = "category")
    private List<PlateEntity> plateList;

}
