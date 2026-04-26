package com.reservairbnb.reservairbnb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "properties")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private Double precioPorNoche;
    private Integer capacidadPersonas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}