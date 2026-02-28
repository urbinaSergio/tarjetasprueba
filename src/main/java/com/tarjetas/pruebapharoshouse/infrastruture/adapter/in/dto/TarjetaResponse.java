package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto;

import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTarjeta;

public record TarjetaResponse(
        String identificador,
        String panEnmascarado,
        String titular,
        EstadoTarjeta estado
) {}
