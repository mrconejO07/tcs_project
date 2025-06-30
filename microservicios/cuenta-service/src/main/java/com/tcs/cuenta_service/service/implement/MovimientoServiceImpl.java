package com.tcs.cuenta_service.service.implement;

import com.tcs.cuenta_service.enums.ETipoMovimiento;
import com.tcs.cuenta_service.exceptions.CustomExceptions;
import com.tcs.cuenta_service.model.dto.MovimientoCrearDTO;
import com.tcs.cuenta_service.model.dto.MovimientoSimpleDTO;
import com.tcs.cuenta_service.model.dto.mapper.IMovimientoCrearDTOMapper;
import com.tcs.cuenta_service.model.dto.mapper.IMovimientoSimpleDTOMapper;
import com.tcs.cuenta_service.model.entity.Cuenta;
import com.tcs.cuenta_service.model.entity.Movimiento;
import com.tcs.cuenta_service.repository.CuentaRepository;
import com.tcs.cuenta_service.repository.MovimientoRepository;
import com.tcs.cuenta_service.service.IMovimientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MovimientoServiceImpl implements IMovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final IMovimientoSimpleDTOMapper movimientoSimpleDTOMapper;
    private final IMovimientoCrearDTOMapper movimientoCrearDTOMapper;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                 CuentaRepository cuentaRepository,
                                 IMovimientoSimpleDTOMapper movimientoSimpleDTOMapper,
                                 IMovimientoCrearDTOMapper movimientoCrearDTOMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoSimpleDTOMapper = movimientoSimpleDTOMapper;
        this.movimientoCrearDTOMapper = movimientoCrearDTOMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoSimpleDTO> listarMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();

        return movimientoSimpleDTOMapper.toDtoList(movimientos);
    }

    @Override
    @Transactional
    public MovimientoSimpleDTO crearMovimiento(MovimientoCrearDTO dto) {
        // Obtener el ID de la cuenta asociada al movimiento
        Long cuentaId = dto.getCuentaId();

        // Buscar la cuenta en la base de datos; lanzar excepción si no existe
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new CustomExceptions.NotFoundException("Cuenta con ID " + cuentaId + " no encontrada"));

        BigDecimal saldoActual = cuenta.getSaldo();
        BigDecimal valorMovimiento = dto.getValor();

        // Validar que el valor del movimiento no sea nulo ni cero
        if (valorMovimiento == null || valorMovimiento.compareTo(BigDecimal.ZERO) == 0) {
            throw new CustomExceptions.BadRequestException("El valor del movimiento debe ser distinto de cero.");
        }

        // Obtener y validar el tipo de movimiento
        ETipoMovimiento tipoMovimiento = dto.getTipoMovimiento();
        if (tipoMovimiento == null) {
            throw new CustomExceptions.BadRequestException("El tipo de movimiento no puede ser nulo.");
        }

        // Validar saldo suficiente para movimientos de tipo DEBITO
        if (tipoMovimiento == ETipoMovimiento.DEBITO && saldoActual.compareTo(valorMovimiento) < 0) {
            throw new CustomExceptions.BadRequestException("Saldo no disponible.");
        }

        // Calcular el nuevo saldo de la cuenta según el tipo de movimiento
        BigDecimal nuevoSaldo = tipoMovimiento == ETipoMovimiento.CREDITO
                ? saldoActual.add(valorMovimiento)   // Suma para crédito
                : saldoActual.subtract(valorMovimiento);  // Resta para débito

        // Actualizar el saldo de la cuenta y guardar cambios en la base de datos
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = movimientoCrearDTOMapper.toEntity(dto);
        movimiento.setCuenta(cuenta);
        movimiento.setSaldoDisponible(nuevoSaldo);

        movimiento = movimientoRepository.save(movimiento);

        return movimientoSimpleDTOMapper.toDto(movimiento);
    }
}