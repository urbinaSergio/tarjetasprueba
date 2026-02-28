package com.tarjetas.pruebapharoshouse.domain.dto;

public record EnrolarTarjetaCommand(
        String identificador,
        Integer numeroValidacion
) {}
