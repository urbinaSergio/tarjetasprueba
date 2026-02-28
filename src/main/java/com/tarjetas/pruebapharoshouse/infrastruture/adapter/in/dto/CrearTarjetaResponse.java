package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto;

public record CrearTarjetaResponse(
        String codigo,
        String mensaje,
        Integer numeroValidacion,
        String panEnmascarado,
        String identificador
) {}
