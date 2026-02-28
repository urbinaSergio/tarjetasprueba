package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarjetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaEntity {

    @Id
    private String id;

    private String pan;
    private String titular;
    private String cedula;
    private String tipo;
    private String telefono;
    private String estado;
    private Integer numeroValidacion;
    private LocalDate fechaCreacion;
}
