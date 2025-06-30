package com.tcs.cliente_service.utils;

import com.tcs.cliente_service.model.dto.ErrorApiResponseDTO;
import com.tcs.cliente_service.model.dto.StandardApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class ApiResponseUtil {
    private ApiResponseUtil() {
    }

    public static <T> ResponseEntity<StandardApiResponseDTO<T>> createApiResponse(String message, T data) {
        StandardApiResponseDTO<T> response = new StandardApiResponseDTO<>(
                LocalDateTime.now(),
                message,
                data,
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<ErrorApiResponseDTO> createErrorResponse(
            String title, String message, String uri, HttpStatus status) {

        ErrorApiResponseDTO response = new ErrorApiResponseDTO(
                LocalDateTime.now(),
                title,
                message,
                uri,
                status.value()
        );

        return new ResponseEntity<>(response, status);
    }
}