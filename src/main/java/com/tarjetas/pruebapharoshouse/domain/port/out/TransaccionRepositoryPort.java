package com.tarjetas.pruebapharoshouse.domain.port.out;

import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TransaccionRepositoryPort {

    Transaccion save(Transaccion transaccion);

    Optional<Transaccion> findByReferencia(String referencia);

    List<Transaccion> findAll();
}
