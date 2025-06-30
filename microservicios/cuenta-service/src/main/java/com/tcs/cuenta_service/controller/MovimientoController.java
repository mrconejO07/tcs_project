package com.tcs.cuenta_service.controller;

import com.tcs.cuenta_service.model.dto.MovimientoCrearDTO;
import com.tcs.cuenta_service.model.dto.MovimientoSimpleDTO;
import com.tcs.cuenta_service.model.dto.StandardApiResponseDTO;
import com.tcs.cuenta_service.service.IMovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.cuenta_service.utils.ApiResponseUtil.createApiResponse;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final IMovimientoService movimientoService;

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<StandardApiResponseDTO<List<MovimientoSimpleDTO>>> listarMovimientos() {
        List<MovimientoSimpleDTO> movimientos = movimientoService.listarMovimientos();
        return createApiResponse("Listado de movimientos exitoso", movimientos);
    }

    @PostMapping
    public ResponseEntity<StandardApiResponseDTO<MovimientoSimpleDTO>> crearMovimientos(@RequestBody MovimientoCrearDTO movimientoDTO) {
        MovimientoSimpleDTO nuevoMovimiento = movimientoService.crearMovimiento(movimientoDTO);
        String message = "Movimiento creado exitosamente";

        return createApiResponse(message, nuevoMovimiento);
    }
}