package com.ejemplo.demo.domain.service;

public class EstadoManualService {

    private Integer valor = 0;

    public Integer actualizar(Integer nuevoValor) {
        this.valor = nuevoValor;
        return this.valor;
    }

    public Integer obtener() {
        return this.valor;
    }
}