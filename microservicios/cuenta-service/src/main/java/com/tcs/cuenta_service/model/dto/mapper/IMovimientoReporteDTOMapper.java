package com.tcs.cuenta_service.model.dto.mapper;

import com.tcs.cuenta_service.model.dto.MovimientoReporteDTO;
import com.tcs.cuenta_service.model.entity.Movimiento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMovimientoReporteDTOMapper {
    MovimientoReporteDTO toDto(Movimiento entidad);

    Movimiento toEntity(MovimientoReporteDTO dto);

    List<MovimientoReporteDTO> toDtoList(List<Movimiento> entidades);
}