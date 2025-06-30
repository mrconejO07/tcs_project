package com.tcs.cuenta_service.model.dto;

import com.tcs.cuenta_service.enums.ETipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoReporteDTO {
    private Long id;
    private LocalDateTime fechaCreacion;
    private ETipoMovimiento tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
}