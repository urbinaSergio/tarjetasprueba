package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.dto.CrearTransaccionCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.CrearTransaccionResponse;

public interface CrearTransaccionUseCase {
    CrearTransaccionResponse crear(CrearTransaccionCommand command);
}
