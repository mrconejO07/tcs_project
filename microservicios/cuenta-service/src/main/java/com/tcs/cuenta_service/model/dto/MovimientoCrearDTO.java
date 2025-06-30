package com.tcs.cuenta_service.model.dto;

import com.tcs.cuenta_service.enums.ETipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovimientoCrearDTO {
    private ETipoMovimiento tipoMovimiento;
    private BigDecimal valor;
    private Long cuentaId;
}