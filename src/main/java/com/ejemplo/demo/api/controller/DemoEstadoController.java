package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.EstadoResponse;
import com.ejemplo.demo.domain.service.EstadoManualService;
import com.ejemplo.demo.domain.service.EstadoSingletonService;
import com.ejemplo.demo.generated.api.DemoEstadoApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private final EstadoSingletonService estadoSingletonService;

    public DemoEstadoController(EstadoSingletonService estadoSingletonService) {
        this.estadoSingletonService = estadoSingletonService;
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarSingleton(Integer valor) {

        Integer valorActual = estadoSingletonService.actualizar(valor);

        return ResponseEntity.ok(
                new EstadoResponse("singleton", valorActual)
        );
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerSingleton() {

        return ResponseEntity.ok(
                new EstadoResponse(
                        "singleton",
                        estadoSingletonService.obtener()
                )
        );
    }

    @Override
    public ResponseEntity<EstadoResponse> reiniciarSingleton() {

        return ResponseEntity.ok(
                new EstadoResponse(
                        "singleton",
                        estadoSingletonService.reiniciar()
                )
        );
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarManual(Integer valor) {

        EstadoManualService service = new EstadoManualService();

        Integer valorActual = service.actualizar(valor);

        return ResponseEntity.ok(
                new EstadoResponse("manual", valorActual)
        );
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerManual() {

        EstadoManualService service = new EstadoManualService();

        return ResponseEntity.ok(
                new EstadoResponse("manual", service.obtener())
        );
    }
}