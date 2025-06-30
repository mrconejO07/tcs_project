package com.tcs.cuenta_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteEstadoCuentaDTO {
    private Long clienteId;
    private String nombreCliente;
    private List<CuentaReporteDTO> cuentas;
}