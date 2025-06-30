package com.tcs.cuenta_service.service.implement;

import com.tcs.cuenta_service.clientes.ClienteClient;
import com.tcs.cuenta_service.exceptions.CustomExceptions;
import com.tcs.cuenta_service.model.Cliente;
import com.tcs.cuenta_service.model.dto.CuentaReporteDTO;
import com.tcs.cuenta_service.model.dto.MovimientoReporteDTO;
import com.tcs.cuenta_service.model.dto.ReporteEstadoCuentaDTO;
import com.tcs.cuenta_service.model.dto.StandardApiResponseDTO;
import com.tcs.cuenta_service.model.dto.mapper.ICuentaReporteDTOMapper;
import com.tcs.cuenta_service.model.dto.mapper.IMovimientoReporteDTOMapper;
import com.tcs.cuenta_service.model.entity.Cuenta;
import com.tcs.cuenta_service.model.entity.Movimiento;
import com.tcs.cuenta_service.repository.CuentaRepository;
import com.tcs.cuenta_service.repository.MovimientoRepository;
import com.tcs.cuenta_service.service.IReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReporteServiceImpl implements IReporteService {
    private final ClienteClient clienteClient;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final ICuentaReporteDTOMapper cuentaReporteDTOMapper;
    private final IMovimientoReporteDTOMapper movimientoReporteDTOMapper;

    public ReporteServiceImpl(ClienteClient clienteRepository,
                              CuentaRepository cuentaRepository,
                              MovimientoRepository movimientoRepository,
                              ICuentaReporteDTOMapper cuentaReporteDTOMapper, IMovimientoReporteDTOMapper movimientoReporteDTOMapper) {
        this.clienteClient = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.cuentaReporteDTOMapper = cuentaReporteDTOMapper;
        this.movimientoReporteDTOMapper = movimientoReporteDTOMapper;
    }

    @Override
    public ReporteEstadoCuentaDTO obtenerEstadoCuenta(Long clienteId, LocalDate desde, LocalDate hasta) {
        // Convertir fechas a LocalDateTime
        LocalDateTime desdeInicio = desde.atStartOfDay();
        LocalDateTime hastaFin = hasta.atTime(LocalTime.MAX);

        // Llamada Feign
        ResponseEntity<StandardApiResponseDTO<Cliente>> clienteResponse = clienteClient.obtenerClientePorId(clienteId);

        if (clienteResponse == null || clienteResponse.getBody() == null || clienteResponse.getBody().getData() == null) {
            throw new CustomExceptions.BadRequestException("Cliente no encontrado desde cliente-service");
        }

        Cliente cliente = clienteResponse.getBody().getData();

        // Obtener cuentas
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        // Mapear cuentas y movimientos
        List<CuentaReporteDTO> cuentaDTOs = cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaCreacionBetween(cuenta, desdeInicio, hastaFin);
            List<MovimientoReporteDTO> movimientosDTO = movimientoReporteDTOMapper.toDtoList(movimientos);

            CuentaReporteDTO dto = cuentaReporteDTOMapper.toDto(cuenta);
            dto.setMovimientos(movimientosDTO);
            return dto;
        }).toList();

        return new ReporteEstadoCuentaDTO(cliente.getId(), cliente.getNombres(), cuentaDTOs);
    }
}