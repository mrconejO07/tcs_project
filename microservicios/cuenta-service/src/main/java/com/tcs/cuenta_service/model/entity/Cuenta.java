package com.tcs.cuenta_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cuenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldo;

    private Boolean estado;

    private Long clienteId; // para relacionar con ClienteClient

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<Movimiento> movimientos;

    @PrePersist
    private void prePersist() {
        this.estado = true;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}