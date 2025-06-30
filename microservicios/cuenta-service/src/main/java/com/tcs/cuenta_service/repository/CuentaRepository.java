package com.tcs.cuenta_service.repository;

import com.tcs.cuenta_service.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
CuentaRepository extends JpaRepository<Cuenta, Long> {
    // Método para buscar cuentas por clienteId
    List<Cuenta> findByClienteId(Long clienteId);
}