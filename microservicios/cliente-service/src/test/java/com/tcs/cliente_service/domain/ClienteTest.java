package com.tcs.cliente_service.domain;

import com.tcs.cliente_service.enums.EGenero;
import com.tcs.cliente_service.model.entity.Cliente;
import com.tcs.cliente_service.service.IClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    @Autowired
    private IClienteService clienteService;


    @Test
    void crearClienteCorrectamente() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Jhon");
        cliente.setApellidos("Aguirre");
        cliente.setEdad(30);
        cliente.setGenero(EGenero.valueOf("M"));
        cliente.setIdentificacion("1003114251");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("0999999999");

        assertAll("Validar cliente",
                () -> assertEquals("Jhon", cliente.getNombres()),
                () -> assertEquals("Aguirre", cliente.getApellidos()),
                () -> assertEquals(30, cliente.getEdad()),
                () -> assertEquals(EGenero.M, cliente.getGenero()),
                () -> assertEquals("1003114251", cliente.getIdentificacion())
        );
    }

    @Test
    void crearCliente_SinContrasena_deberiaPermitirContrasenaNull() {
        Cliente cliente = new Cliente();
        cliente.setContrasena(null);

        assertNull(cliente.getContrasena());
    }
}