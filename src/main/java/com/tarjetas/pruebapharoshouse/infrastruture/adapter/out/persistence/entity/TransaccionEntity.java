package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionEntity {

    @Id
    @Column(length = 6)
    private String referencia;

    @Column(name = "tarjeta_id", nullable = false)
    private String tarjetaId;

    @Column(name = "total_compra", nullable = false)
    private Double totalCompra;

    @Column(name = "direccion_compra", nullable = false)
    private String direccionCompra;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fecha;
}
