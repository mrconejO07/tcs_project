package com.tcs.cliente_service.service;

import com.tcs.cliente_service.exceptions.CustomExceptions;
import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;
import com.tcs.cliente_service.model.dto.mapper.IClienteSimpleDTOMapper;
import com.tcs.cliente_service.model.entity.Cliente;
import com.tcs.cliente_service.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private IClienteSimpleDTOMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;  // tu clase que implementa IClienteService

    @Test
    void obtenerClientePorId_deberiaRetornarClienteCuandoExiste() {
        Cliente entidad = new Cliente();
        entidad.setId(1L);
        entidad.setNombres("Jhon Freddy");
        entidad.setApellidos("Aguirre");
        entidad.setIdentificacion("9999999999");
        entidad.setEstado(true);

        ClienteSimpleDTO dto = new ClienteSimpleDTO();
        dto.setId(1L);
        dto.setNombres("Jhon Freddy");
        dto.setIdentificacion("9999999999");
        dto.setEstado(true);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(entidad));
        when(clienteMapper.toDto(entidad)).thenReturn(dto);

        ClienteSimpleDTO resultado = clienteService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Jhon Freddy", resultado.getNombres());
        assertEquals("9999999999", resultado.getIdentificacion());

        verify(clienteRepository).findById(1L);
        verify(clienteMapper).toDto(entidad);
    }

    @Test
    void crearClienteConEdadNegativa_DeberiaLanzarExcepcion() {
        ClienteCrearDTO clienteDto = new ClienteCrearDTO();
        clienteDto.setEdad(-5);

        // Suponiendo que tu servicio lanza IllegalArgumentException para edad negativa
        assertThrows(CustomExceptions.BadRequestException.class, () -> {
            clienteService.crear(clienteDto);
        });
    }
}