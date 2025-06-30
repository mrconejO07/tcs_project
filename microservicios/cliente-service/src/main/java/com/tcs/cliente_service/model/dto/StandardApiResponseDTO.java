package com.tcs.cliente_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardApiResponseDTO<T> {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private T data;
    private String status;
    private int statusCode;
}
