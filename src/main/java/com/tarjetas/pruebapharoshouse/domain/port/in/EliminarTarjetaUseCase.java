package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.dto.EliminarTarjetaCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.GenericResponse;

public interface EliminarTarjetaUseCase {
    GenericResponse eliminar(EliminarTarjetaCommand command);
}
