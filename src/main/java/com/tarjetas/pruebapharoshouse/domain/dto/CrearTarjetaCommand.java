package com.tarjetas.pruebapharoshouse.domain.dto;

import com.tarjetas.pruebapharoshouse.domain.enums.TipoTarjeta;

public record CrearTarjetaCommand(
        String pan,
        String titular,
        String cedula,
        TipoTarjeta tipo,
        String telefono
) {}
