package com.tcs.cliente_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteCreadoEventDTO implements Serializable {
    private Long id;
    private String nombres;
    private String identificacion;
    @Serial
    private static final long serialVersionUID = 1L;
}