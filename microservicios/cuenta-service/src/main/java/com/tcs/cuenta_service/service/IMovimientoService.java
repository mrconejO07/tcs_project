package com.tcs.cuenta_service.service;

import com.tcs.cuenta_service.model.dto.MovimientoCrearDTO;
import com.tcs.cuenta_service.model.dto.MovimientoSimpleDTO;

import java.util.List;

public interface IMovimientoService {
    List<MovimientoSimpleDTO> listarMovimientos();

    MovimientoSimpleDTO crearMovimiento(MovimientoCrearDTO dto);
}