package com.tarjetas.pruebapharoshouse.domain.dto;

public record CrearTransaccionCommand(
        String identificador,
        String numeroReferencia,
        Double totalCompra,
        String direccionCompra
) {}
