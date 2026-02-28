package com.tarjetas.pruebapharoshouse.domain.model;

import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTarjeta;
import com.tarjetas.pruebapharoshouse.domain.enums.TipoTarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {

    private String id; // identificador hash
    private String pan;
    private String titular;
    private String cedula;
    private TipoTarjeta tipo;
    private String telefono;
    private EstadoTarjeta estado;
    private Integer numeroValidacion;
    private LocalDate fechaCreacion;



    // métodos de dominio
}
