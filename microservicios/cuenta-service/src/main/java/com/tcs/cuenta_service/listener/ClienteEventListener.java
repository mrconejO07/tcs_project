package com.tcs.cuenta_service.listener;

import com.tcs.cuenta_service.event.ClienteCreadoEventDTO;
import com.tcs.cuenta_service.model.entity.Cuenta;
import com.tcs.cuenta_service.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClienteEventListener {

    private final CuentaRepository cuentaRepository;

    @RabbitListener(queues = "cliente.creado.queue")
    public void manejarClienteCreado(ClienteCreadoEventDTO evento) {
        System.out.println("::: Evento recibido - Cliente: " + evento.getNombres() + " :::");

        Cuenta cuenta = new Cuenta();
        cuenta.setClienteId(evento.getId());
        cuenta.setNumeroCuenta(UUID.randomUUID().toString().substring(0, 10));
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setTipoCuenta("AHORROS");
        cuentaRepository.save(cuenta);
    }
}