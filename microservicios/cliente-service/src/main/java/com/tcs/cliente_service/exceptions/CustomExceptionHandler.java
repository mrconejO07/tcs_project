package com.tcs.cliente_service.exceptions;

import com.tcs.cliente_service.model.dto.ErrorApiResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.stream.Collectors;

import static com.tcs.cliente_service.utils.ApiResponseUtil.createErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleMissingServletRequestPartException(MissingServletRequestPartException ex,
                                                                                        HttpServletRequest request) {
        String missingPart = ex.getRequestPartName();

        return createErrorResponse(
                "Parte de la solicitud faltante",
                "Error en la solicitud: falta un archivo obligatorio (" + missingPart + ")",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleConstraintViolationException(ConstraintViolationException ex,
                                                                                  HttpServletRequest request) {
        String entityName = ex.getConstraintViolations().stream()
                .map(violation -> violation.getRootBeanClass().getSimpleName())
                .findFirst()
                .orElse("Desconocida");

        String detalles = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("; "));

        return createErrorResponse(
                "Violación de restricción",
                "Entidad: " + entityName + " -> " + detalles,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CustomExceptions.BadRequestException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleBadRequestException(CustomExceptions.BadRequestException ex, HttpServletRequest request) {
        return createErrorResponse(
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleDataAccessException(DataAccessException ex, HttpServletRequest request) {
        String detalles = ex.getMessage() != null
                ? ex.getMostSpecificCause().getMessage()
                : "Error desconocido en la base de datos";

        return createErrorResponse(
                "Database Error",
                detalles,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CustomExceptions.NotFoundException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleNotFoundException(CustomExceptions.NotFoundException ex, HttpServletRequest request) {
        return createErrorResponse(
                "Recurso no encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String objectName = ex.getBindingResult().getObjectName();
        String detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return createErrorResponse(
                "Error de validación de campos",
                "Entidad: " + objectName + " - " + detalles,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CustomExceptions.InvalidFieldException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleInvalidFieldException(CustomExceptions.InvalidFieldException ex, HttpServletRequest request) {
        return createErrorResponse(
                "Campo inválido",
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }

    // Manejo de excepciones no previstas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage() != null ? ex.getMessage() : "Error inesperado";

        return createErrorResponse(
                "Error Interno del Servidor",
                errorMsg,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CustomExceptions.ValorYaAsignadoException.class)
    public ResponseEntity<ErrorApiResponseDTO> handleValorYaAsignado(CustomExceptions.ValorYaAsignadoException ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage() != null ? ex.getMessage() : "Error inesperado";

        return createErrorResponse(
                "Conflicto por valor ya asignado",
                errorMsg,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST
        );
    }
}