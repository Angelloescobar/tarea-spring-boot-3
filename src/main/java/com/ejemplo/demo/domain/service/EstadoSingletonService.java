package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class EstadoSingletonService {

    private Integer valor = 0;

    public Integer actualizar(Integer nuevoValor) {
        this.valor = nuevoValor;
        return this.valor;
    }

    public Integer obtener() {
        return this.valor;
    }

    public Integer reiniciar() {
        this.valor = 0;
        return this.valor;
    }
}