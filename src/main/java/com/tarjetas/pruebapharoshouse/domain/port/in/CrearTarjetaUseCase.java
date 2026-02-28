package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.dto.CrearTarjetaCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.CrearTarjetaResponse;

public interface CrearTarjetaUseCase {
    CrearTarjetaResponse crear(CrearTarjetaCommand command);
}
