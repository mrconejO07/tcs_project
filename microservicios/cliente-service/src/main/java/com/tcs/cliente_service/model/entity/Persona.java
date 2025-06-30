package com.tcs.cliente_service.model.entity;

import com.tcs.cliente_service.enums.EGenero;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Persona implements Serializable {
    @NotBlank(message = "es obligatorio, no puede estar vacío")
    @Size(max = 50, message = "tiene un máximo de caracteres permitidos")
    @Column(length = 50, nullable = false)
    private String nombres;

    @NotBlank(message = "es obligatorio, no puede estar vacío")
    @Size(max = 50, message = "tiene un máximo de caracteres permitidos")
    @Column(length = 50, nullable = false)
    private String apellidos;

    @Enumerated(EnumType.STRING)
    private EGenero genero;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 150, message = "La edad no puede ser mayor a 150")
    private Integer edad;

    @NotBlank(message = "es obligatorio, no puede estar vacío")
    @Size(min = 10, max = 15, message = "debe tener entre 10 y 15 caracteres")
    @Column(length = 15, unique = true)
    private String identificacion;

    private String direccion;

    @Pattern(regexp = "^\\d{1,20}$", message = "es obligatorio y debe contener máximo 20 números")
    @Column(length = 20)
    private String telefono;

    @Serial
    private static final long serialVersionUID = 1L;
}