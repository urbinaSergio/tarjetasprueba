package com.tarjetas.pruebapharoshouse.domain.model;

import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTransaccion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {

    private String referencia;
    private String tarjetaId;
    private Double totalCompra;
    private String direccionCompra;
    private EstadoTransaccion estado;
    private LocalDateTime fecha;

    // 🔥 MÉTODO 1
    public boolean puedeAnularse() {
        return Duration.between(this.fecha, LocalDateTime.now())
                .toMinutes() < 5
                && this.estado == EstadoTransaccion.APROBADA;
    }

    // 🔥 MÉTODO 2
    public void anular() {
        this.estado = EstadoTransaccion.ANULADA;
    }
}
