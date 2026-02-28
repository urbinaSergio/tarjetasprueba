package com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.rest;

import com.tarjetas.pruebapharoshouse.domain.dto.*;
import com.tarjetas.pruebapharoshouse.domain.port.in.*;
import com.tarjetas.pruebapharoshouse.infrastruture.adapter.in.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TarjetaController {

    private final CrearTarjetaUseCase crearUseCase;
    private final EnrolarTarjetaUseCase enrolarUseCase;

    private final ConsultarTarjetaUseCase consultarUseCase;
    private final EliminarTarjetaUseCase eliminarUseCase;
    private final CrearTransaccionUseCase crearTransaccionUseCase;
    private final AnularTransaccionUseCase anularTransaccionUseCase;

    private final ListarTarjetasUseCase listarTarjetasUseCase;

    private final ListarTransaccionesUseCase listarUseCase;

    public TarjetaController(CrearTarjetaUseCase crearUseCase,
                             EnrolarTarjetaUseCase enrolarUseCase, ConsultarTarjetaUseCase consultarUseCase, EliminarTarjetaUseCase eliminarUseCase, CrearTransaccionUseCase crearTransaccionUseCase, AnularTransaccionUseCase anularTransaccionUseCase, ListarTarjetasUseCase listarTarjetasUseCase, ListarTransaccionesUseCase listarUseCase) {
        this.crearUseCase = crearUseCase;
        this.enrolarUseCase = enrolarUseCase;
        this.consultarUseCase = consultarUseCase;
        this.eliminarUseCase = eliminarUseCase;
        this.crearTransaccionUseCase = crearTransaccionUseCase;
        this.anularTransaccionUseCase = anularTransaccionUseCase;
        this.listarTarjetasUseCase = listarTarjetasUseCase;
        this.listarUseCase = listarUseCase;
    }

    @PostMapping("/creartarjeta")
    public CrearTarjetaResponse crear(@RequestBody CrearTarjetaCommand command) {
        return crearUseCase.crear(command);
    }

    @PostMapping("/enrolar")
    public EnrolarTarjetaResponse enrolar(@RequestBody EnrolarTarjetaCommand command) {
        return enrolarUseCase.enrolar(command);
    }

    @GetMapping("/tarjetas")
    public ConsultarTarjetaResponse consultar(@RequestParam String identificador) {
        return consultarUseCase.consultar(identificador);
    }

    @DeleteMapping("/tarjetas")
    public GenericResponse eliminar(@RequestBody EliminarTarjetaCommand command) {
        return eliminarUseCase.eliminar(command);
    }

    @PostMapping("/transacciones")
    public CrearTransaccionResponse crearTransaccion(
            @RequestBody CrearTransaccionCommand command) {
        return crearTransaccionUseCase.crear(command);
    }

    @PostMapping("/transacciones/anular")
    public AnularTransaccionResponse anular(
            @RequestBody AnularTransaccionCommand command) {
        return anularTransaccionUseCase.anular(command);
    }

    @GetMapping("listartarjetas")
    public List<TarjetaResponse> listar() {

        return listarTarjetasUseCase.listar()
                .stream()
                .map(t -> new TarjetaResponse(
                        t.getId(),
                        t.getPan(),
                        t.getTitular(),
                        t.getEstado()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("listartransacciones")
    public List<TransaccionResponse> listarTransacciones() {
        return listarUseCase.listarTransaciones()
                .stream()
                .map(t -> new TransaccionResponse(
                        t.getTarjetaId(),
                        t.getReferencia(),
                        t.getTotalCompra(),
                        t.getDireccionCompra(),
                        t.getEstado().name(),
                        t.getFecha()
                ))
                .toList();
    }
}
