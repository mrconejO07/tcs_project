package com.tcs.cliente_service.service;

import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteSimpleDTO> listarTodos();

    ClienteSimpleDTO obtenerPorId(Long id);

    ClienteSimpleDTO crear(ClienteCrearDTO cliente);

    ClienteSimpleDTO actualizar(ClienteSimpleDTO cliente);

    void eliminar(Long id);
}
