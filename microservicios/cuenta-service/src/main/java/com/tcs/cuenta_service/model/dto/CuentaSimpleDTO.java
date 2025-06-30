package com.tcs.cuenta_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaSimpleDTO {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;
    private Boolean estado;
    private Long clienteId; // para relacionar con cliente

    public CuentaSimpleDTO(String numeroCuenta,
                           String tipoCuenta,
                           BigDecimal saldo,
                           List<MovimientoSimpleDTO> movimientosDTO) {
    }
}