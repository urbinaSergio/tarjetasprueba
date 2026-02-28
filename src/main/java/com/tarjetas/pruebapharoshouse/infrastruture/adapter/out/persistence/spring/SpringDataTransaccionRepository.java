package com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.spring;

import com.tarjetas.pruebapharoshouse.infrastruture.adapter.out.persistence.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransaccionRepository
        extends JpaRepository<TransaccionEntity, String> {
}
