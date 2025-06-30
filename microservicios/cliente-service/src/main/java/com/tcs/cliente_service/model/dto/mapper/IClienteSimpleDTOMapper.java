package com.tcs.cliente_service.model.dto.mapper;

import com.tcs.cliente_service.model.dto.ClienteSimpleDTO;
import com.tcs.cliente_service.model.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteSimpleDTOMapper {
    ClienteSimpleDTO toDto(Cliente entity);

    Cliente toEntity(ClienteSimpleDTO dto);

    List<ClienteSimpleDTO> toDtoList(List<Cliente> usuarios);
}