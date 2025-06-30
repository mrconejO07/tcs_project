package com.tcs.cuenta_service.model;

import com.tcs.cuenta_service.enums.EGenero;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Persona {
    private String nombres;
    private String apellidos;
    private EGenero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}