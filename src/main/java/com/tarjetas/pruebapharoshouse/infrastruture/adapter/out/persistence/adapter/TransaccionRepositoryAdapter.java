package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.adapter;

import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTransaccion;
import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;
import com.tarjetas.pruebapharoshouse.domain.port.out.TransaccionRepositoryPort;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity.TransaccionEntity;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.spring.SpringDataTransaccionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransaccionRepositoryAdapter implements TransaccionRepositoryPort {

    private final SpringDataTransaccionRepository repository;

    public TransaccionRepositoryAdapter(SpringDataTransaccionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        TransaccionEntity entity = TransaccionEntity.builder()
                .referencia(transaccion.getReferencia())
                .tarjetaId(transaccion.getTarjetaId())
                .totalCompra(transaccion.getTotalCompra())
                .direccionCompra(transaccion.getDireccionCompra())
                .estado(transaccion.getEstado().name())
                .fecha(java.time.LocalDateTime.now())
                .build();

        repository.save(entity);

        return transaccion;
    }

    @Override
    public Optional<Transaccion> findByReferencia(String referencia) {
        return repository.findById(referencia)
                .map(entity -> new Transaccion(
                        entity.getReferencia(),
                        entity.getTarjetaId(),
                        entity.getTotalCompra(),
                        entity.getDireccionCompra(),
                        EstadoTransaccion.valueOf(entity.getEstado()),
                        entity.getFecha()
                ));
    }

    @Override
    public List<Transaccion> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Transaccion toDomain(TransaccionEntity entity) {
        return new Transaccion(
                entity.getReferencia(),
                entity.getTarjetaId(),
                entity.getTotalCompra(),
                entity.getDireccionCompra(),
                EstadoTransaccion.valueOf(entity.getEstado()),
                entity.getFecha()
        );
    }


}
