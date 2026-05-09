package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.HealthResponse;
import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;
import com.ejemplo.demo.generated.api.WorkshopApi;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController implements WorkshopApi {

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @Override
    public ResponseEntity<HealthResponse> getWorkshopHealth() {

        return ResponseEntity.ok(
                new HealthResponse(
                        "ok",
                        "Workshop Spring Boot activo"
                )
        );
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorGet(String nombre) {

        return ResponseEntity.ok(
                saludoService.crearSaludo(nombre)
        );
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorPost(
            @Valid SaludoRequest saludoRequest
    ) {

        return ResponseEntity.ok(
                saludoService.crearSaludo(
                        saludoRequest.nombre()
                )
        );
    }
}