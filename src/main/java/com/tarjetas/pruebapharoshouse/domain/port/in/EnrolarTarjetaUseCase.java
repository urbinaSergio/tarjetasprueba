package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.dto.EnrolarTarjetaCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.EnrolarTarjetaResponse;

public interface EnrolarTarjetaUseCase {
    EnrolarTarjetaResponse enrolar(EnrolarTarjetaCommand command);
}
