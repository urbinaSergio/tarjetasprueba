package com.tarjetas.pruebapharoshouse.aplication.service;

import com.tarjetas.pruebapharoshouse.domain.dto.AnularTransaccionCommand;
import com.tarjetas.pruebapharoshouse.domain.dto.CrearTransaccionCommand;
import com.tarjetas.pruebapharoshouse.domain.enums.EstadoTransaccion;
import com.tarjetas.pruebapharoshouse.domain.model.Transaccion;
import com.tarjetas.pruebapharoshouse.domain.port.in.AnularTransaccionUseCase;
import com.tarjetas.pruebapharoshouse.domain.port.in.CrearTransaccionUseCase;
import com.tarjetas.pruebapharoshouse.domain.port.in.ListarTransaccionesUseCase;
import com.tarjetas.pruebapharoshouse.domain.port.out.TarjetaRepositoryPort;
import com.tarjetas.pruebapharoshouse.domain.port.out.TransaccionRepositoryPort;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.AnularTransaccionResponse;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.CrearTransaccionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionService implements CrearTransaccionUseCase, AnularTransaccionUseCase, ListarTransaccionesUseCase {

    private final TarjetaRepositoryPort tarjetaRepository;
    private final TransaccionRepositoryPort transaccionRepository;

    public TransaccionService(TarjetaRepositoryPort tarjetaRepository,
                              TransaccionRepositoryPort transaccionRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public AnularTransaccionResponse anular(AnularTransaccionCommand command) {
        var transaccionOpt = transaccionRepository
                .findByReferencia(command.numeroReferencia());

        if (transaccionOpt.isEmpty()) {
            return new AnularTransaccionResponse(
                    "01",
                    "Número de referencia inválido",
                    command.numeroReferencia()
            );
        }

        var transaccion = transaccionOpt.get();

        if (!transaccion.puedeAnularse()) {
            return new AnularTransaccionResponse(
                    "02",
                    "No se puede anular transacción",
                    command.numeroReferencia()
            );
        }

        transaccion.anular();
        transaccionRepository.save(transaccion);

        return new AnularTransaccionResponse(
                "00",
                "Compra anulada",
                command.numeroReferencia()
        );
    }

    @Override
    public CrearTransaccionResponse crear(CrearTransaccionCommand command) {
        var tarjetaOpt = tarjetaRepository.findById(command.identificador());

        if (tarjetaOpt.isEmpty()) {
            return new CrearTransaccionResponse(
                    "01",
                    "Tarjeta no existe",
                    "RECHAZADA",
                    command.numeroReferencia()
            );
        }

        var tarjeta = tarjetaOpt.get();

        if (!tarjeta.getEstado().name().equals("ENROLADA")) {
            return new CrearTransaccionResponse(
                    "02",
                    "Tarjeta no enrolada",
                    "RECHAZADA",
                    command.numeroReferencia()
            );
        }

        Transaccion transaccion = new Transaccion(
                command.numeroReferencia(),
                command.identificador(),
                command.totalCompra(),
                command.direccionCompra(),
                EstadoTransaccion.APROBADA,
                LocalDateTime.now()
        );

        transaccionRepository.save(transaccion);

        return new CrearTransaccionResponse(
                "00",
                "Compra exitosa",
                "APROBADA",
                command.numeroReferencia()
        );
    }


    @Override
    public List<Transaccion> listarTransaciones() {
        return transaccionRepository.findAll();
    }
}
