package com.tcs.cuenta_service.service;

import com.tcs.cuenta_service.model.dto.CuentaSimpleDTO;
import com.tcs.cuenta_service.model.dto.ReporteEstadoCuentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ICuentaService {
    List<CuentaSimpleDTO> listarCuentas();

    CuentaSimpleDTO crearCuenta(CuentaSimpleDTO cuentaSimpleDTO);

    CuentaSimpleDTO actualizarCuenta(CuentaSimpleDTO cuentaSimpleDTO);

    void eliminarCuenta(String cuentaId);

    CuentaSimpleDTO obtenerCuentaPorId(String cuentaId);
}