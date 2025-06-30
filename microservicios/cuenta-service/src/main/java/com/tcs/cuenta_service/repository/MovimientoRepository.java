package com.tcs.cuenta_service.repository;

import com.tcs.cuenta_service.model.entity.Cuenta;
import com.tcs.cuenta_service.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaId(Long cuentaId);

    List<Movimiento> findByCuentaAndFechaCreacionBetween(Cuenta cuenta, LocalDateTime desde, LocalDateTime hasta);
}