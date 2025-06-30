package com.tcs.cuenta_service.model.dto.mapper;

import com.tcs.cuenta_service.model.dto.CuentaSimpleDTO;
import com.tcs.cuenta_service.model.entity.Cuenta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICuentaSimpleDTOMapper {
    CuentaSimpleDTO toDto(Cuenta entidad);

    Cuenta toEntity(CuentaSimpleDTO dto);

    List<CuentaSimpleDTO> toDtoList(List<Cuenta> entidad);
}