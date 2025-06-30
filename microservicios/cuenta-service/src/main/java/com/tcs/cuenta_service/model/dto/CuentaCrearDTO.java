package com.tcs.cuenta_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaCrearDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Long clienteId;
}