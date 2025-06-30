package com.tcs.cuenta_service.service;

import com.tcs.cuenta_service.model.dto.ReporteEstadoCuentaDTO;

import java.time.LocalDate;

public interface IReporteService {
    ReporteEstadoCuentaDTO obtenerEstadoCuenta(Long clienteId, LocalDate desde, LocalDate hasta);
}