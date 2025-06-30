package com.tcs.cliente_service.controller;

import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;
import com.tcs.cliente_service.model.dto.StandardApiResponseDTO;
import com.tcs.cliente_service.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.cliente_service.utils.ApiResponseUtil.createApiResponse;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<StandardApiResponseDTO<List<ClienteSimpleDTO>>> listarClientes() {
        List<ClienteSimpleDTO> clientes = clienteService.listarTodos();
        String message = "Lista de clientes obtenida correctamente";

        return createApiResponse(message, clientes);
    }

    @PostMapping
    public ResponseEntity<StandardApiResponseDTO<ClienteSimpleDTO>> crearCliente(
            @RequestBody @Valid ClienteCrearDTO clienteDTO) {

        ClienteSimpleDTO clienteCreado = clienteService.crear(clienteDTO);
        String message = "Cliente creado correctamente con ID: " + clienteCreado.getId();

        return createApiResponse(message, clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<ClienteSimpleDTO>> actualizarCliente(
            @PathVariable("id") Long clienteId,
            @RequestBody @Valid ClienteSimpleDTO clienteDTO) {

        clienteDTO.setId(clienteId);
        ClienteSimpleDTO clienteActualizado = clienteService.actualizar(clienteDTO);
        String message = "Cliente con ID " + clienteId + " actualizado correctamente";

        return createApiResponse(message, clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<Void>> eliminarCliente(
            @PathVariable("id") Long clienteId) {

        clienteService.eliminar(clienteId);
        String message = "Cliente con ID " + clienteId + " eliminado correctamente";

        return createApiResponse(message, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardApiResponseDTO<ClienteSimpleDTO>> obtenerClientePorId(
            @PathVariable("id") Long clienteId) {

        ClienteSimpleDTO cliente = clienteService.obtenerPorId(clienteId);
        String message = "Cliente con ID " + clienteId + " encontrado";

        return createApiResponse(message, cliente);
    }
}