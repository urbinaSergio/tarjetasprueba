package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.adapter;

import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTarjeta;
import com.tarjetas.pruebapharoshouse.domain.enums.TipoTarjeta;
import com.tarjetas.pruebapharoshouse.domain.model.Tarjeta;
import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;
import com.tarjetas.pruebapharoshouse.domain.port.out.TarjetaRepositoryPort;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity.TarjetaEntity;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.spring.SpringDataTarjetaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class TarjetaRepositoryAdapter  implements TarjetaRepositoryPort {

    private final SpringDataTarjetaRepository repository;

    public TarjetaRepositoryAdapter(SpringDataTarjetaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        TarjetaEntity entity = TarjetaEntity.builder()
                .id(tarjeta.getId())
                .pan(tarjeta.getPan())
                .titular(tarjeta.getTitular())
                .cedula(tarjeta.getCedula())
                .tipo(tarjeta.getTipo().name())
                .telefono(tarjeta.getTelefono())
                .estado(tarjeta.getEstado().name())
                .numeroValidacion(tarjeta.getNumeroValidacion())
                .fechaCreacion(java.time.LocalDate.now())
                .build();

        repository.save(entity);

        return tarjeta;
    }

    @Override
    public Optional<Tarjeta> findById(String id) {
        return repository.findById(id)
                .map(entity -> new Tarjeta(
                        entity.getId(),
                        entity.getPan(),
                        entity.getTitular(),
                        entity.getCedula(),
                        TipoTarjeta.valueOf(entity.getTipo()),
                        entity.getTelefono(),
                        EstadoTarjeta.valueOf(entity.getEstado()),
                        entity.getNumeroValidacion(),
                        entity.getFechaCreacion()
                ));
    }

    @Override
    public List<Tarjeta> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> findAllTransaciones() {
        return List.of();
    }

    private Tarjeta toDomain(TarjetaEntity entity) {
        return new Tarjeta(
                entity.getId(),
                entity.getPan(),
                entity.getTitular(),
                entity.getCedula(),
                TipoTarjeta.valueOf(entity.getTipo()),
                entity.getTelefono(),
                EstadoTarjeta.valueOf(entity.getEstado()),
                entity.getNumeroValidacion(),
                entity.getFechaCreacion()
        );
    }
}
