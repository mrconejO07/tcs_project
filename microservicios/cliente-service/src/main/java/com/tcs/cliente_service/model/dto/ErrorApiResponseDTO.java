package com.tcs.cliente_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorApiResponseDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private String message;
    private String path;
    private Integer statusCode;
}
