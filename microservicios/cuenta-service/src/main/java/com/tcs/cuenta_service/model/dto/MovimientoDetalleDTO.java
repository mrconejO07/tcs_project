package com.tcs.cuenta_service.model.dto;

import com.tcs.cuenta_service.enums.ETipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovimientoDetalleDTO {
    private Long id;
    private LocalDateTime fechaCreacion;
    private ETipoMovimiento tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
    private CuentaCrearDTO cuenta;
}