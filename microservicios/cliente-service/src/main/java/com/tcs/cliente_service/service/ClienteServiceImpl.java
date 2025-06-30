package com.tcs.cliente_service.service;

import com.tcs.cliente_service.enums.EGenero;
import com.tcs.cliente_service.exceptions.CustomExceptions;
import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;
import com.tcs.cliente_service.model.dto.mapper.IClienteCrearDTOMapper;
import com.tcs.cliente_service.model.dto.mapper.IClienteSimpleDTOMapper;
import com.tcs.cliente_service.model.entity.Cliente;
import com.tcs.cliente_service.publisher.ClienteEventPublisher;
import com.tcs.cliente_service.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteEventPublisher clienteEventPublisher;

    private final ClienteRepository clienteRepository;
    private final IClienteSimpleDTOMapper clienteSimpleDTOMapper;
    private final IClienteCrearDTOMapper clienteCrearDTOMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              IClienteSimpleDTOMapper clienteSimpleDTOMapper,
                              IClienteCrearDTOMapper clienteCrearDTOMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteSimpleDTOMapper = clienteSimpleDTOMapper;
        this.clienteCrearDTOMapper = clienteCrearDTOMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteSimpleDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clienteSimpleDTOMapper.toDtoList(clientes);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteSimpleDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Cliente con ID: " + id + " no encontrado"));

        return clienteSimpleDTOMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClienteSimpleDTO crear(ClienteCrearDTO clienteDTO) {
        if (clienteDTO.getIdentificacion() != null && !clienteDTO.getIdentificacion().isBlank()) {
            Optional<Cliente> usuarioExistente = clienteRepository.findByIdentificacion(clienteDTO.getIdentificacion());

            if (usuarioExistente.isPresent()) {
                throw new CustomExceptions.ValorYaAsignadoException(
                        String.format("La identificación '%s' ya está registrada.", clienteDTO.getIdentificacion())
                );
            }
        }

        String rawPassword = clienteDTO.getContrasena();
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new CustomExceptions.BadRequestException("La contraseña no debe estar vacía.");
        }

        clienteDTO.setContrasena(passwordEncoder.encode(rawPassword));

        Cliente clienteEntity = clienteCrearDTOMapper.toEntity(clienteDTO);
        clienteEntity = clienteRepository.save(clienteEntity);

        clienteEventPublisher.enviarClienteCreado(clienteEntity);

        return clienteSimpleDTOMapper.toDto(clienteEntity);
    }

    @Override
    @Transactional
    public ClienteSimpleDTO actualizar(ClienteSimpleDTO clienteDTO) {
        Long id = clienteDTO.getId();

        if (!clienteRepository.existsById(id)) {
            throw new CustomExceptions.NotFoundException("Cliente con ID: " + id + " no encontrado");
        }

        Cliente clienteEntity = clienteSimpleDTOMapper.toEntity(clienteDTO);

        clienteEntity.setNombres(clienteDTO.getNombres());
        clienteEntity.setApellidos(clienteDTO.getApellidos());
        clienteEntity.setGenero(EGenero.valueOf(clienteDTO.getGenero()));
        clienteEntity.setEdad(clienteDTO.getEdad());
        clienteEntity.setIdentificacion(clienteDTO.getIdentificacion());
        clienteEntity.setDireccion(clienteDTO.getDireccion());
        clienteEntity.setTelefono(clienteDTO.getTelefono());
        clienteEntity.setFechaCreacion(clienteDTO.getFechaCreacion());
        clienteEntity.setEstado(clienteDTO.getEstado());

        Cliente clienteActualizado = clienteRepository.save(clienteEntity);

        return clienteSimpleDTOMapper.toDto(clienteActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Cliente con ID: " + id + " no encontrado"));

        clienteRepository.delete(cliente);
    }
}