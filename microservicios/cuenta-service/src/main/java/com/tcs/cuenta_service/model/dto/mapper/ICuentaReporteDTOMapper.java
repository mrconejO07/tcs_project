package com.tcs.cuenta_service.model.dto.mapper;

import com.tcs.cuenta_service.model.dto.CuentaReporteDTO;
import com.tcs.cuenta_service.model.dto.MovimientoReporteDTO;
import com.tcs.cuenta_service.model.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ICuentaReporteDTOMapper {
    @Mapping(target = "movimientos", expression = "java(mapMovimientos(entidad))")
    @Mapping(target = "saldoActual", source = "saldo")
    CuentaReporteDTO toDto(Cuenta entidad);

    Cuenta toEntity(CuentaReporteDTO dto);

    List<CuentaReporteDTO> toDtoList(List<Cuenta> entidades);

    default List<MovimientoReporteDTO> mapMovimientos(Cuenta entidad) {
        if (entidad.getMovimientos() == null || entidad.getMovimientos().isEmpty()) {
            return Collections.emptyList();
        }

        return entidad.getMovimientos()
                .stream()
                .map(precio -> {
                    MovimientoReporteDTO movimientoReporteDTO = new MovimientoReporteDTO();
                    movimientoReporteDTO.setId(precio.getId());
                    movimientoReporteDTO.setFechaCreacion(precio.getFechaCreacion());
                    movimientoReporteDTO.setTipoMovimiento(precio.getTipoMovimiento());
                    movimientoReporteDTO.setValor(precio.getValor());
                    movimientoReporteDTO.setSaldoDisponible(precio.getSaldoDisponible());

                    return movimientoReporteDTO;
                })
                .collect(Collectors.toList());
    }
}