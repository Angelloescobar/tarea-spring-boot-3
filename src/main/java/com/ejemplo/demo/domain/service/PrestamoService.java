package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public PrestamoResponse simularPrestamo(PrestamoRequest request) {
        BigDecimal monto = request.monto();
        BigDecimal tasaAnual = request.tasaAnual();
        Integer meses = request.meses();

        if (monto == null || tasaAnual == null || meses == null) {
            throw new IllegalArgumentException("Los datos del préstamo son obligatorios");
        }

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        if (tasaAnual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasaAnual debe ser mayor a 0");
        }

        if (meses < 1 || meses > 360) {
            throw new IllegalArgumentException("Los meses deben estar entre 1 y 360");
        }

        MathContext mc = new MathContext(20, RoundingMode.HALF_UP);

        BigDecimal tasaMensual = tasaAnual
                .divide(BigDecimal.valueOf(12), mc)
                .divide(BigDecimal.valueOf(100), mc);

        double r = tasaMensual.doubleValue();
        int n = meses;
        double p = monto.doubleValue();

        double cuota;
        if (r == 0) {
            cuota = p / n;
        } else {
            cuota = p * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        }

        BigDecimal cuotaMensual = BigDecimal.valueOf(cuota).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(monto).setScale(2, RoundingMode.HALF_UP);

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}