package com.tarjetas.pruebapharoshouse.aplication.service;

import com.tarjetas.pruebapharoshouse.domain.dto.CrearTarjetaCommand;
import com.tarjetas.pruebapharoshouse.domain.dto.EliminarTarjetaCommand;
import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;
import com.tarjetas.pruebapharoshouse.domain.port.in.*;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.ConsultarTarjetaResponse;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.CrearTarjetaResponse;
import com.tarjetas.pruebapharoshouse.domain.dto.EnrolarTarjetaCommand;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.EnrolarTarjetaResponse;
import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTarjeta;
import com.tarjetas.pruebapharoshouse.domain.model.Tarjeta;
import com.tarjetas.pruebapharoshouse.domain.port.out.TarjetaRepositoryPort;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.GenericResponse;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class TarjetaService implements CrearTarjetaUseCase, EnrolarTarjetaUseCase, ConsultarTarjetaUseCase, EliminarTarjetaUseCase, ListarTarjetasUseCase {

    private final TarjetaRepositoryPort repository;



    public TarjetaService(TarjetaRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CrearTarjetaResponse crear(CrearTarjetaCommand command) {
        int numeroValidacion = new Random().nextInt(100) + 1;

        String identificador = generarHash(command.pan());

        Tarjeta tarjeta = new Tarjeta(
                identificador,
                command.pan(),
                command.titular(),
                command.cedula(),
                command.tipo(),
                command.telefono(),
                EstadoTarjeta.CREADA,
                numeroValidacion,
                LocalDate.now()
        );

        repository.save(tarjeta);

        return new CrearTarjetaResponse(
                "00",
                "Éxito",
                numeroValidacion,
                enmascararPan(command.pan()),
                identificador
        );
    }

    @Override
    public EnrolarTarjetaResponse enrolar(EnrolarTarjetaCommand command) {
        Tarjeta tarjeta = repository.findById(command.identificador())
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        if (!tarjeta.getNumeroValidacion().equals(command.numeroValidacion())) {
            return new EnrolarTarjetaResponse("01", "Número de validación incorrecto");
        }

        tarjeta.setEstado(EstadoTarjeta.ENROLADA);
        repository.save(tarjeta);

        return new EnrolarTarjetaResponse("00", "Tarjeta enrolada correctamente");
    }

    private String generarHash(String pan) {
        try {
            String input = pan + LocalDate.now();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error generando hash");
        }
    }

    private String enmascararPan(String pan) {
        return pan.substring(0,6)
                + "****"
                + pan.substring(pan.length()-4);
    }

    @Override
    public ConsultarTarjetaResponse consultar(String identificador) {
        Tarjeta tarjeta = repository.findById(identificador)
                .orElseThrow(() -> new RuntimeException("Tarjeta no existe"));

        return new ConsultarTarjetaResponse(
                enmascararPan(tarjeta.getPan()),
                tarjeta.getTitular(),
                tarjeta.getCedula(),
                tarjeta.getTelefono(),
                tarjeta.getEstado().name()
        );
    }

    @Override
    public GenericResponse eliminar(EliminarTarjetaCommand command) {
        Tarjeta tarjeta = repository.findById(command.identificador())
                .orElseThrow(() -> new RuntimeException("Tarjeta no existe"));

        tarjeta.setEstado(EstadoTarjeta.INACTIVA);
        repository.save(tarjeta);

        return new GenericResponse("00", "Se ha eliminado la tarjeta");
    }

    @Override
    public List<Tarjeta> listar() {
        return repository.findAll();
    }
}
