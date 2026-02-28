package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto;

public record CrearTransaccionResponse(
        String codigo,
        String mensaje,
        String estadoTransaccion,
        String numeroReferencia
) {}
