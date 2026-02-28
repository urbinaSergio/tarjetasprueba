package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto;

public record ConsultarTarjetaResponse(
        String panEnmascarado,
        String titular,
        String cedula,
        String telefono,
        String estado
) {}
