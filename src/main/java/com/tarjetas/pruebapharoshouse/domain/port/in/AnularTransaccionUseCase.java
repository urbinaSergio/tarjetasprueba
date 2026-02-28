package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.dto.AnularTransaccionCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.AnularTransaccionResponse;

public interface AnularTransaccionUseCase {
    AnularTransaccionResponse anular(AnularTransaccionCommand command);
}
