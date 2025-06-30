package com.tcs.cuenta_service.controller;

import com.tcs.cuenta_service.exceptions.CustomExceptions;
import com.tcs.cuenta_service.model.dto.CuentaSimpleDTO;
import com.tcs.cuenta_service.model.dto.StandardApiResponseDTO;
import com.tcs.cuenta_service.service.ICuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.cuenta_service.utils.ApiResponseUtil.createApiResponse;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final ICuentaService cuentaService;

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<StandardApiResponseDTO<List<CuentaSimpleDTO>>> listarCuentas() {
        List<CuentaSimpleDTO> cuentas = cuentaService.listarCuentas();
        return createApiResponse("Listado de cuentas exitoso", cuentas);
    }

    @PostMapping
    public ResponseEntity<StandardApiResponseDTO<CuentaSimpleDTO>> crearCuenta(@RequestBody CuentaSimpleDTO cuentaDTO) {
        CuentaSimpleDTO nuevaCuenta = cuentaService.crearCuenta(cuentaDTO);
        return createApiResponse("Cuenta creada exitosamente", nuevaCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<CuentaSimpleDTO>> actualizarCuenta(
            @PathVariable("id") Long id,
            @RequestBody CuentaSimpleDTO cuentaDTO) {

        // Asegurar que el id path coincida con el id del DTO
        if (!id.equals(cuentaDTO.getId())) {
            throw new CustomExceptions.BadRequestException("El ID en la URL no coincide con el ID en el cuerpo");
        }

        CuentaSimpleDTO cuentaActualizada = cuentaService.actualizarCuenta(cuentaDTO);
        return createApiResponse("Cuenta actualizada exitosamente", cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<Void>> eliminarCuenta(@PathVariable("id") Long id) {
        cuentaService.eliminarCuenta(String.valueOf(id));
        return createApiResponse("Cuenta eliminada exitosamente", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<CuentaSimpleDTO>> obtenerCuentaPorId(@PathVariable("id") Long id) {
        CuentaSimpleDTO cuenta = cuentaService.obtenerCuentaPorId(String.valueOf(id));
        return createApiResponse("Cuenta obtenida exitosamente", cuenta);
    }
}