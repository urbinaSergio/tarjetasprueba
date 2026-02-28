package com.tarjetas.pruebapharoshouse.domain.port.in;

import com.tarjetas.pruebapharoshouse.domain.model.Tarjeta;

import java.util.List;

public interface ListarTarjetasUseCase {
    List<Tarjeta> listar();
}
