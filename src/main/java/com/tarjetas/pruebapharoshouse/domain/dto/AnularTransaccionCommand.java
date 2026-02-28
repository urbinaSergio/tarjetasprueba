package com.tarjetas.pruebapharoshouse.domain.dto;

public record AnularTransaccionCommand(
        String identificador,
        String numeroReferencia,
        Double totalCompra
) {}
