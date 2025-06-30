package com.tcs.cuenta_service.clientes;

import com.tcs.cuenta_service.model.Cliente;
import com.tcs.cuenta_service.model.dto.StandardApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cliente-service", url = "${cliente.service.url}", path = "/clientes")
public interface ClienteClient {
    @GetMapping("/{id}")
    ResponseEntity<StandardApiResponseDTO<Cliente>> obtenerClientePorId(@PathVariable("id") Long clienteId);

    @PutMapping("/{id}")
    ResponseEntity<?> updateClient(@PathVariable("id") Long clienteId, @RequestBody Cliente clienteDTO);
}