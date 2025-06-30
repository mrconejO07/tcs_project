package com.tcs.cuenta_service.model.dto.mapper;

import com.tcs.cuenta_service.model.dto.MovimientoCrearDTO;
import com.tcs.cuenta_service.model.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMovimientoCrearDTOMapper {
    Movimiento toEntity(MovimientoCrearDTO dto);
}
