package com.tarjetas.pruebapharoshouse.domain.port.out;

import com.tarjetas.pruebapharoshouse.domain.model.Tarjeta;
import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepositoryPort {
    Tarjeta save(Tarjeta tarjeta);
    Optional<Tarjeta> findById(String id);
    List<Tarjeta> findAll();
    List<Transaccion> findAllTransaciones();
}
