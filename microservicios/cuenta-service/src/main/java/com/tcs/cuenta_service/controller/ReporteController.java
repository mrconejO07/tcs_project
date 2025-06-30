package com.tcs.cuenta_service.controller;

import com.tcs.cuenta_service.clientes.ClienteClient;
import com.tcs.cuenta_service.exceptions.CustomExceptions;
import com.tcs.cuenta_service.model.Cliente;
import com.tcs.cuenta_service.model.dto.ReporteEstadoCuentaDTO;
import com.tcs.cuenta_service.model.dto.StandardApiResponseDTO;
import com.tcs.cuenta_service.service.IReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.tcs.cuenta_service.utils.ApiResponseUtil.createApiResponse;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final IReporteService reporteService;
    private final ClienteClient clienteClient;

    public ReporteController(IReporteService reporteService, ClienteClient clienteClient) {
        this.reporteService = reporteService;
        this.clienteClient = clienteClient;
    }

    @GetMapping
    public ResponseEntity<StandardApiResponseDTO<ReporteEstadoCuentaDTO>> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        ReporteEstadoCuentaDTO reporte = reporteService.obtenerEstadoCuenta(clienteId, desde, hasta);
        String message = "Reporte generado correctamente para el cliente con ID " + clienteId;

        return createApiResponse(message, reporte);
    }

    @GetMapping("/test")
    public ResponseEntity<StandardApiResponseDTO<Cliente>> testEndpoint() {
        ResponseEntity<StandardApiResponseDTO<Cliente>> clienteResponse = clienteClient.obtenerClientePorId(1L);

        if (clienteResponse == null || clienteResponse.getBody() == null || clienteResponse.getBody().getData() == null) {
            throw new CustomExceptions.BadRequestException("Cliente no encontrado desde cliente-service");
        }

        Cliente cliente = clienteResponse.getBody().getData();
        return createApiResponse("Cliente obtenido correctamente desde cliente-service", cliente);
    }
}