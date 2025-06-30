package com.tcs.cliente_service.model.dto.mapper;

import com.tcs.cliente_service.model.dto.ClienteCrearDTO;
import com.tcs.cliente_service.model.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteCrearDTOMapper {
    ClienteCrearDTO toDto(Cliente entity);

    Cliente toEntity(ClienteCrearDTO dto);

    List<ClienteCrearDTO> toDtoList(List<Cliente> usuarios);
}