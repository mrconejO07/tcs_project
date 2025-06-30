//package com.tcs.cliente_service.service;
//
//import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
//import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;
//import com.tcs.cliente_service.repository.ClienteRepository;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional  // para que cada test haga rollback y no afecte la DB
//class ClienteServiceIntegrationTest {
//
//    @Autowired
//    private IClienteService clienteService; // usa la interfaz o implementación
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Test
//    void crearClienteYObtenerPorId_deberiaGuardarYRecuperarCliente() {
//        // Preparar DTO para crear cliente
//        ClienteCrearDTO clienteCrearDTO = new ClienteCrearDTO();
//        clienteCrearDTO.setNombres("Jhon");
//        clienteCrearDTO.setApellidos("Freddy");
//        clienteCrearDTO.setIdentificacion("1234567890");
//        clienteCrearDTO.setEdad(30);
//        clienteCrearDTO.setEstado(true);
//
//        // Crear cliente
//        ClienteSimpleDTO creado = clienteService.crear(clienteCrearDTO);
//
//        // Validar que el cliente se creó
//        assertNotNull(creado);
//        assertNotNull(creado.getId());
//
//        // Recuperar cliente directamente del servicio para validar
//        ClienteSimpleDTO recuperado = clienteService.obtenerPorId(creado.getId());
//
//        assertNotNull(recuperado);
//        assertEquals("Ana", recuperado.getNombres());
//        assertEquals("1234567890", recuperado.getIdentificacion());
//
//        // Opcional: verificar que está en el repositorio
//        assertTrue(clienteRepository.existsById(creado.getId()));
//    }
//}