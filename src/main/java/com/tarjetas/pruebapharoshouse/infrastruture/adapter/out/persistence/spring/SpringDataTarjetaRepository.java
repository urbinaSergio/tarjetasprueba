package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.spring;

import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTarjetaRepository extends JpaRepository<TarjetaEntity, String> {
}
