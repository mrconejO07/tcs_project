package com.tcs.cuenta_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tcs.cuenta_service.enums.ETipoMovimiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private ETipoMovimiento tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldoDisponible;

    @JsonIgnoreProperties(value = {"direccionesTemporales", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    @PrePersist
    private void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @Serial
    private static final long serialVersionUID = 1L;
}