package com.tcs.cuenta_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente extends Persona {
    private Long id;
    private LocalDateTime fechaCreacion;
    private String contrasena;
    private Boolean estado;
}