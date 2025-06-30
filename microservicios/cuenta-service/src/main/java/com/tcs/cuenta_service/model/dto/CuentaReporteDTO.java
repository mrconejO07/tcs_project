package com.tcs.cuenta_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaReporteDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private List<MovimientoReporteDTO> movimientos;
}