package com.tcs.cliente_service.publisher;

import com.tcs.cliente_service.event.ClienteCreadoEventDTO;
import com.tcs.cliente_service.model.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void enviarClienteCreado(Cliente cliente) {
        ClienteCreadoEventDTO evento = new ClienteCreadoEventDTO(
                cliente.getId(),
                cliente.getNombres(),
                cliente.getIdentificacion()
        );

        rabbitTemplate.convertAndSend("cliente.exchange", "cliente.creado", evento);
    }
}
