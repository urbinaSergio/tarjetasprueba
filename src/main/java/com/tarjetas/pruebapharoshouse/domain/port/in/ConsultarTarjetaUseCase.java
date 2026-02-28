package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.ConsultarTarjetaResponse;

public interface ConsultarTarjetaUseCase {
    ConsultarTarjetaResponse consultar(String identificador);
}
