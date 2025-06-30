package com.tcs.cuenta_service.model.dto.mapper;

import com.tcs.cuenta_service.model.dto.MovimientoSimpleDTO;
import com.tcs.cuenta_service.model.entity.Movimiento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMovimientoSimpleDTOMapper {
    MovimientoSimpleDTO toDto(Movimiento entidad);

    List<MovimientoSimpleDTO> toDtoList(List<Movimiento> entidades);
}