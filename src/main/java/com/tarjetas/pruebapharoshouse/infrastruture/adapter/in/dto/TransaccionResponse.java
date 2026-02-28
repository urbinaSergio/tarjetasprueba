package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto;

import java.time.LocalDateTime;


public record TransaccionResponse(
        String referencia,
        String tarjetaId,
        Double totalCompra,
        String direccionCompra,
        String estado,
        LocalDateTime fecha
) {}
